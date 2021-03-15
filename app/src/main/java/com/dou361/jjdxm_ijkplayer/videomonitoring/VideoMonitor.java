package com.dou361.jjdxm_ijkplayer.videomonitoring;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.dou361.jjdxm_ijkplayer.R;
import com.dou361.jjdxm_ijkplayer.videomonitoring.utlis.MediaUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class VideoMonitor extends Activity implements View.OnClickListener , MyRadioGroup.OnCheckedChangeListener {

    public String hostURL="http://vehicleroadcloud.faw.cn:60443/backend/appBackend/";
    public CountDownTimer countDownTimer;
    public VideoRequest videoRequest;
    public VideoReply videoReply,videoReply2;
    public String videoResponseString,mergeVideoString;//视频Post请求返回数据
    private PlayerView player;
    private Context mContext;
    private List<VideoijkBean> list;
    private PowerManager.WakeLock wakeLock;
    View rootView;
    private Integer videoPlayingNum=-1;
    private MyRadioGroup videoRatioGroup;
    private RadioButton buttonFront, buttonBack, buttonLeft, buttonRight, channelGodPerspective;
    private HashMap<String, RadioButton> channels = new HashMap<>(5);
    private ImageButton lightControllerButton;


    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        rootView = getLayoutInflater().from(this).inflate(R.layout.activity_videomonitor, null);
        setContentView(rootView);
//        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        lightControllerButton=(ImageButton) findViewById(R.id.light);
        lightControllerButton.setOnClickListener(this);

        buttonFront = (RadioButton) findViewById(R.id.front_Click);
        buttonBack = (RadioButton)findViewById(R.id.back_Click);
        buttonLeft = (RadioButton)findViewById(R.id.left_Click);
        buttonRight = (RadioButton)findViewById(R.id.right_Click);
        channelGodPerspective = (RadioButton)findViewById(R.id.god_perspective_Click);
        buttonFront.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        buttonLeft.setOnClickListener(this);
        buttonRight.setOnClickListener(this);
        channelGodPerspective.setOnClickListener(this);
        channels.put("Channel_Front",buttonFront);
        channels.put("Channel_Back",buttonBack);
        channels.put("Channel_Left",buttonLeft);
        channels.put("Channel_Right",buttonRight);
        channels.put("Channel_God_Perspective", channelGodPerspective);
//
//        replyTextView=findViewById(R.id.replyTextView);
//        requestTextView=findViewById(R.id.requestTextView);


        /**常亮*/
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();


        videoRatioGroup = (MyRadioGroup)findViewById(R.id.radiogroup);
        videoRatioGroup.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                videoReply = new VideoReply("原始");
                videoReply2 = new VideoReply("融合");
                switch (checkedId){
                    case R.id.left_Click:
                        try2play(3);
                        break;
                    case R.id.right_Click:
                        try2play(4);
                        break;
                    case R.id.front_Click:
                        try2play(0);
                        break;
                    case R.id.back_Click:
                        try2play(2);
                        break;
                    case R.id.god_perspective_Click:
                        try2play(5);
                        break;
                    default:
                        Toast defualt =Toast.makeText(VideoMonitor.this, "上帝", Toast.LENGTH_SHORT);
                        new ShowMyToast().showMyToast(defualt,100);
                        break;
                }
            }
        });
    }

    private void try2play(final int videoNum) {
        //请求第videoNum的视频
        Log.d(TAG, "嘗試播放: "+videoNum+"路視頻，正在播放"+videoPlayingNum+"路視頻");
        if(videoPlayingNum==videoNum){return;}
        else if((videoPlayingNum==1||videoPlayingNum==6)&&(videoNum==0)){
            Log.d(TAG, "try2play: "+"當前播放視頻就是待播放視頻的時候，不需操作");
            return;}
        //當前播放視頻就是待播放視頻的時候，不需操作
        videoReply.initialVideoReply();
        videoReply2.initialVideoReply();

        if (videoNum!=0){
            //for循环关闭其他所有视频以节省流量
            for(int i=1;i<7;i++){/*
                if(i!=videoNum)
                postVideoRequest(i,0);*/
            }
        countDownTimer=new CountDownTimer(10000,1000) {
            int i=0;
            @Override
            public void onTick(long millisUntilFinished) {
                i=i+1;
//                replyTextView.setText("执行第"+i+"次请求："+videoReply.toString());
//                Log.d(TAG, "onTick: "+"执行第"+i+"次请求："+videoReply.toString());
                if (videoReply.getCode().equals("InitialString")){
                    String replyOriginalString = postVideoRequest(videoNum,1);
                    Log.d(TAG, "onTick: replyOriginalString"+replyOriginalString);
                    if(replyOriginalString!=null){
                        //当请求数据不为空时，设置videoReply类，，，，，反序列化操作即 由字符串--->对象
                        videoReply = JSON.parseObject(replyOriginalString, VideoReply.class);
                        Log.d(TAG, "返回: videoReply1："+videoReply.toString()+"\nvideoReply2："+videoReply2.toString());
                        if(videoReply.getCode().equals("0030000")){
                            playVideo(videoNum);
                            cancel();
                        }else Toast.makeText(VideoMonitor.this,"請求視頻失败！",Toast.LENGTH_LONG).show();

                    }
                }
            }
            @Override
            public void onFinish() {
            }
        }.start();}
        else {//播放前视角视频时有四种情况
            for(int i=2;i<6;i++){//for循環關閉其他線路
                /*if(i!=videoNum)
                    postVideoRequest(i,1);*/}
            countDownTimer=new CountDownTimer(5000,1000) {
                int i=0;
                @Override
                public void onTick(long millisUntilFinished) {
                    i=i+1;
                    Log.d(TAG, "onTick: "+"执行第"+i+"次请求：原始"+videoReply.getCode()+"融合"+videoReply2.getCode());
                    if(videoReply.getCode().equals("0030000")){//原始視頻已請求成功
                        if(videoReply2.getCode().equals("0030000")){//全部成功
                            Log.d(TAG, i+"onTick: videoReply1："+videoReply.toString()+"\nvideoReply2："+videoReply2.toString());
                            onFinish();//對code的四種狀態進行判斷
                            cancel();
                        }
                        else {//融合視頻未成功
                            String replyMergeRetry=postVideoRequest(videoNum+6,1);
                            if(replyMergeRetry!=null){
                                videoReply2 = JSON.parseObject(replyMergeRetry, VideoReply.class);
                                Log.d(TAG, "onTick: 原始视频成功，融合失败");}
                        }
                    }
                    else {//原始視頻未成功
                        if (videoReply2.getCode().equals("0030000")){//融合视频成功

                            String replyOriginalRetry = postVideoRequest(videoNum+1,1);
                            if(replyOriginalRetry!=null){
                                videoReply = JSON.parseObject(replyOriginalRetry, VideoReply.class);//繼續請求原始視頻
                                Log.d(TAG, "onTick: 融合视频成功，原始失败");}

                            }
                        else {//均未成功
                        String replyOriginalFront = postVideoRequest(videoNum+1,1);//VideoNum是0，请求的是原始视频1和融合视频6
                        if(replyOriginalFront!=null){
                            videoReply = JSON.parseObject(replyOriginalFront, VideoReply.class);
                            Log.d(TAG, "onTick: replyOriginalFront1"+videoReply.toString());}

                        String replyMergeFront=postVideoRequest(videoNum+6,1);//VideoNum是0，请求的是原始视频1和融合视频6
                        Log.d(TAG, "onTick: replyMergeFront"+replyMergeFront);
                        if(replyMergeFront!=null){
                            videoReply2 = JSON.parseObject(replyMergeFront, VideoReply.class);//兩個視頻都請求
                            Log.d(TAG, "onTick: replyMergeFront1"+videoReply2.toString());
                        }}
                    }
                }

                @Override
                public void onFinish() {
                    if(videoReply.getCode().equals("0030000")){//原始視頻可用
                        if (videoReply2.getCode().equals("0030000")){
                            Log.d(TAG, "onFinish: 前视角与融合视频同时请求成功");
                            playVideo(0);//最理想情況，同時可播倆視頻
                        }else{
                            Toast.makeText(VideoMonitor.this,"融合視頻不可用，播放原始視頻",Toast.LENGTH_LONG).show();
                            Log.d(TAG, "onFinish: 融合視頻不可用，播放原始視頻");
                            playVideo(1);//融合視頻不可用，播放原始視頻
                        }
                    }
                    else{//原始視頻不可用
                        Toast.makeText(VideoMonitor.this,"原始視頻不可用，播放融合視頻",Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onFinish: 原始視頻不可用，播放融合視頻");
                        if (videoReply2.getCode().equals("0030000")){
                            playVideo(6);//只播融合視頻
                        }else{
                            Toast.makeText(VideoMonitor.this,"請求視頻失败！",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }.start();
        }
    }

    /**
     * Post video request string
     *
     * @param videoNum    video num 视频类型
     * @param serviceType service type 1表示打开，0表示关闭
     * @return the string
     */

    public String postVideoRequest(final int videoNum, int serviceType){
        videoRequest = new VideoRequest();
        videoRequest.setUserId("6D");
        videoRequest.setVin("test");
        videoRequest.setVideo_type(Integer.toString(videoNum));
        videoRequest.setServicetype(Integer.toString(serviceType));
        final String videoRequestJson = JSON.toJSONString(videoRequest);//序列化
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "postVideoRequest: "+videoRequestJson);
                    OkHttpClient videoClient=new OkHttpClient();
                    Request videoRequest= new Request.Builder()
                            .url(hostURL+"videoRequest")
                            .post(RequestBody.create(MediaType.parse("application/json"),videoRequestJson))
                            .build();//创造HTTP请求
                    //执行发送的指令
                    Response videoResponse = videoClient.newCall(videoRequest).execute();
                    Log.d(TAG, "run: 到了videoResponse");
                    if(videoNum!=6){
                    videoResponseString=videoResponse.body().string();
                        Log.d(TAG, "run: videoResponseString:"+videoResponseString);
                    }
                    else {
                        mergeVideoString = videoResponse.body().string();
                        Log.d(TAG, "run: Merge");
                    }
                    Log.d(TAG, "run: 返回结果"+videoNum+"路视频：\n"+videoResponseString+"\n"+mergeVideoString);

                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("POST失敗", "onClick: "+e.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(VideoMonitor.this,"請求視頻失败！",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
        Log.d(TAG, "run: 返回结果"+videoNum+"路视频：\n"+videoResponseString);
        if (videoNum!=6){
        return videoResponseString;}
        else return mergeVideoString;
    }

    public void playVideoUrl( String url){
//      Toast.makeText(VideoMonitor.this, "請求成功！", Toast.LENGTH_SHORT).show();
      player = new PlayerView(VideoMonitor.this, rootView)
              //.setTitle("前摄像")
              .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
              .setScaleType(PlayStateParams.fillparent) //视频界面剪裁设置
              .forbidTouch(false)
              .hideSteam(true)
              .hideMenu(true)
              .hideCenterPlayer(false)
              .setNetWorkTypeTie(false)
              .hideRotation(true) //隐藏旋转按钮
              .setChargeTie(true, 480)//设置最长播放时间
              .showThumbnail(new OnShowThumbnailListener() {
                  @Override
                  public void onShowThumbnail(ImageView ivThumbnail) {
//                                 加载前显示的缩略图
                      Glide.with(mContext)
                              .load(R.drawable.pic_before_video)//"http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg"
                              .placeholder(R.drawable.pic_before_video) //加载成功之前占位图
                              .error(R.color.cl_error)//加载错误之后的错误图
                              .into(ivThumbnail);
                  }
              })
             .setPlaySource(url)
              .startPlay();
    }

    public void playVideo(int videoNum){
        videoPlayingNum=videoNum;
        switch (videoNum){
            case 0:
                //前视角原始视频与融合视频在一个按钮，通过视频窗口内部按钮切换
                Toast.makeText(VideoMonitor.this,"請求成功！",Toast.LENGTH_SHORT).show();
                list = new ArrayList<VideoijkBean>();
                //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
                //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
                String url1 = "rtmp://150.158.176.170/live/test_vin_1";//"rtmp://150.158.176.170/live/1";
                String url2 = "rtmp://150.158.176.170/live/test_vin_6";
                VideoijkBean m1 = new VideoijkBean();
                m1.setStream("原始视频");
                m1.setUrl(url1);
                VideoijkBean m2 = new VideoijkBean();
                m2.setStream("融合视频");
                m2.setUrl(url2);
                list.add(m1);
                list.add(m2);
                player = new PlayerView(this, rootView) {
                    @Override
                    public PlayerView setPlaySource(List<VideoijkBean> list) {
                        return super.setPlaySource(list);
                    }
                }
                        //.setTitle("前摄像")
                        .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                        .setScaleType(PlayStateParams.fillparent) //视频界面剪裁设置
                        .forbidTouch(false)
                        .hideSteam(false)
                        .hideMenu(true)
                        .hideCenterPlayer(true)
                        .setNetWorkTypeTie(false)
                        .hideRotation(true) //隐藏旋转按钮
                        .setChargeTie(true,480)//设置最长播放时间
                        .showThumbnail(new OnShowThumbnailListener() {
                            @Override
                            public void onShowThumbnail(ImageView ivThumbnail) {
//                                 加载前显示的缩略图
                                Glide.with(mContext)
                                        .load(R.drawable.pic_before_video)//"http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg"
                                        .placeholder(R.drawable.pic_before_video) //加载成功之前占位图
                                        .error(R.color.cl_error)//加载错误之后的错误图
                                        .into(ivThumbnail);
                            }
                        })
                        .setPlaySource(list)
                        .startPlay();
                break;
            case 1:
                //只播原始視頻
                playVideoUrl("rtmp://150.158.176.170/live/test_vin_1");
                break;
            case 2:
                //后视角
                playVideoUrl("rtmp://150.158.176.170/live/test_vin_2");
                break;
            case 3:
                //左视角
                playVideoUrl("rtmp://150.158.176.170/live/test_vin_3");
                break;
            case 4:
                //右视角
                playVideoUrl("rtmp://150.158.176.170/live/test_vin_4");
                break;
            case 5:
                //上帝视角
                playVideoUrl("rtmp://150.158.176.170/live/test_vin_5");
                break;
            case 6:
                //只有融合視頻
                playVideoUrl("rtmp://150.158.176.170/live/test_vin_6");
                break;

            default:
                break;
        }
    }


    @OnClick({R.id.light})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.light:
                {   
//                    requestTextView.setText("請求開燈/關燈");
                    }
            break;

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        MediaUtils.muteAudioFocus(mContext, true);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        /**demo的内容，暂停系统其它媒体的状态*/
        MediaUtils.muteAudioFocus(mContext, false);
        /**demo的内容，激活设备常亮状态*/
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        /**demo的内容，恢复设备亮度状态*/
        if (wakeLock != null) {
            wakeLock.release();
        }
    }


    @Override
    public void onCheckedChanged(MyRadioGroup group, int checkedId) {

    }
}
