package com.dou361.jjdxm_ijkplayer.videomonitoring;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.dou361.jjdxm_ijkplayer.R;
import com.dou361.jjdxm_ijkplayer.videomonitoring.utlis.MediaUtils;
import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;

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

public class VideoEZplayer extends Activity implements View.OnClickListener , MyRadioGroup.OnCheckedChangeListener,SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    EZPlayer mEZPlayer;

    private int mUserId;
    private int mChannelNo;
    private int count = 0;
    private String accessToken;

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
    private Integer videoPlayingNum=5;
    private MyRadioGroup videoRatioGroup;
    private RadioButton buttonFront, buttonBack, buttonLeft, buttonRight, channelGodPerspective;
    private HashMap<String, RadioButton> channels = new HashMap<>(5);
    private ImageButton lightControllerButton;


    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcplay);

        mEZPlayer=new EZPlayer();
        Log.d(TAG, "onCreate: "+mEZPlayer.getPlayPort());
//        ezOpenSDK.getInstance().setAccessToken(accessToken);
//        Log.d(TAG, "onCreate: "+ezOpenSDK.getEZAccessToken());
        Log.d(TAG, "onCreate: EZplayer Creating");



        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 获取相机列表");
                try {
                    EZOpenSDK.getInstance().getDeviceList(1,10);
//                    ezOpenSDK.getDeviceList(1,1);
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }).start();


//        mEZPlayer=EZOpenSDK.getInstance().createPlayer("E40958484",1);
//        mEZPlayer = EZOpenSDK.getInstance().createPlayerWithUrl("https://hls01open.ys7.com/openlive/6e0b2be040a943489ef0b9bb344b96b8.hd.m3u8");

        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        mSurfaceHolder = mSurfaceView.getHolder();
        mEZPlayer.setSurfaceHold(mSurfaceHolder);
        mSurfaceHolder.addCallback(this);
        mSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count++%2 == 0){
                    Log.d(TAG, "onClick: count"+count);
                    mEZPlayer.stopRealPlay();
                }else{
                    mEZPlayer.startRealPlay();
                }
            }
        });


        this.mContext = this;
//        rootView = getLayoutInflater().from(this).inflate(R.layout.activity_hcplay, null);
//        setContentView(rootView);
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

//        for(int i=1;i<7;i++){//for循環打開所有視頻流
//            postVideoRequest(i,1);}


        videoRatioGroup = (MyRadioGroup)findViewById(R.id.radiogroup);
        videoRatioGroup.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.left_Click:
                        Log.d(TAG, "onCheckedChanged: 左视角");
                        playVideo(3);
//                        try2play(3);
                        break;
                    case R.id.right_Click:
                        playVideo(4);
//                        try2play(4);
                        break;
                    case R.id.front_Click:
                        playVideo(1);
//                        try2play(0);
                        break;
                    case R.id.back_Click:
                        playVideo(2);
//                        try2play(2);
                        break;
                    case R.id.god_perspective_Click:
                        playVideo(5);
//                        try2play(5);

                        break;
                    default:
                        Toast defualt =Toast.makeText(VideoEZplayer.this, "上帝", Toast.LENGTH_SHORT);
                        new ShowMyToast().showMyToast(defualt,100);
                        break;
                }
            }
        });
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
                            Toast.makeText(VideoEZplayer.this,"請求視頻失败！",Toast.LENGTH_LONG).show();
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



    public void playVideo(int videoNum){
        if(videoPlayingNum==videoNum)return;

        switch (videoNum){
            //E40958484 E40958558 E40958703 E40958817 右左前后序列号
            case 0:
                break;
            case 1:
                //只播原始視頻
                Log.d(TAG, "playVideo: 前视角");
                playViaDevSerial("E40958703",mSurfaceHolder);
                mEZPlayer.stopRealPlay();
                break;
            case 2:
                //后视角
                surfaceCreated(mSurfaceHolder);
                break;
            case 3:
                //左视角
                Log.d(TAG, "playVideo: 左视角");
                mEZPlayer=EZOpenSDK.getInstance().createPlayer("E40958484",1);
                mEZPlayer.startRealPlay();
                break;
            case 4:
                //右视角
                mEZPlayer=EZOpenSDK.getInstance().createPlayer("E40958558",1);
                mEZPlayer.startRealPlay();
                break;
            case 5:
                //上帝视角
                mEZPlayer=EZOpenSDK.getInstance().createPlayer("231236707",1);
                mEZPlayer.startRealPlay();
                break;
            case 6:
                //只有融合視頻
                break;

            default:
                break;
        }
        videoPlayingNum=videoNum;
    }

    public void playViaDevSerial(String deviceSerial,SurfaceHolder holder){
        mEZPlayer=EZOpenSDK.getInstance().createPlayer(deviceSerial,1);
//        mEZPlayer=EZOpenSDK.getInstance().createPlayer("E40958484",1);


        mEZPlayer.setSurfaceHold(holder);
//        mEZPlayer.setHandler(mHandler);
        holder.addCallback(this);
        mEZPlayer.startRealPlay();
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

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated: 播放器创建");
//        mSurfaceHolder = holder;
//        Intent intent =  getIntent();
        mEZPlayer=EZOpenSDK.getInstance().createPlayer("231236707",1);
//        mEZPlayer=EZOpenSDK.getInstance().createPlayer("E40958484",1);


        mEZPlayer.setSurfaceHold(holder);
//        mEZPlayer.setHandler(mHandler);
        mEZPlayer.startRealPlay();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mEZPlayer != null) {
            mEZPlayer.setSurfaceHold(null);
        }
    }
}
