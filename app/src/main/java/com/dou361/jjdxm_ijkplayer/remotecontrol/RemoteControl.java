package com.dou361.jjdxm_ijkplayer.remotecontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnPlayerBackListener;
import com.dou361.ijkplayer.listener.OnPlayerStartOrPauseListener;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.dou361.jjdxm_ijkplayer.MainActivity;
import com.dou361.jjdxm_ijkplayer.R;
import com.dou361.jjdxm_ijkplayer.command.Control;
import com.dou361.jjdxm_ijkplayer.command.Gears;
import com.dou361.jjdxm_ijkplayer.command.Handbrake;
import com.dou361.jjdxm_ijkplayer.command.Video;
import com.dou361.jjdxm_ijkplayer.mqtt.MQTTRequest;
import com.dou361.jjdxm_ijkplayer.mqtt.MQTTSample;
import com.dou361.jjdxm_ijkplayer.service.NtpTime;
import com.dou361.jjdxm_ijkplayer.videomonitoring.VideoMonitor;
import com.dou361.jjdxm_ijkplayer.videomonitoring.VideoReply;
import com.dou361.jjdxm_ijkplayer.videomonitoring.utlis.MediaUtils;
import com.tencent.iot.hub.device.android.core.log.TXMqttLogCallBack;
import com.tencent.iot.hub.device.android.core.util.TXLog;
import com.tencent.iot.hub.device.java.core.common.Status;
import com.tencent.iot.hub.device.java.core.mqtt.TXMqttActionCallBack;


import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.os.SystemClock.uptimeMillis;

/**
 遥控车辆进行移动
 */
public class RemoteControl extends Activity {

    public String hostIP = /*"192.168.0.108:18081";*/"10.6.206.20:30549";
    public String userId = "6D的安卓測試機";
    public String vin = "001";

    private final static String mLogPath = Environment.getExternalStorageDirectory().getPath() + "/tencent/";

    public CountDownTimer countDownTimer;
    private MainActivity mParent;
    private MQTTSample mqttSample;

    private PlayerView player;
    private Context mContext;
    private TextView  Speed;
    private List<VideoijkBean> list;
    private PowerManager.WakeLock wakeLock;
    private View rootView;
    private Activity mActivity;

    private static final String TAG = "FullscreenActivity";
    private boolean braking = true;
    private boolean active_braking=false;
    private int gearGlobal=0;
    private int connectMQTTTimes=0;
    private int handBrakeStatus = 0;
    private Button LlightingButton;
    private ImageButton imageButton_forward,imageButton_backward,imageButton_brake;
    private ImageView app_video_play;
    private Spinner Video_Modul_Spinner;

    private double wheelAngle=0.0;
    private double speed=0.0;
    private NtpTime ntpTime=new NtpTime();



    /*虛擬機*/
//    private String mBrokerURL = "ssl://fawtsp-mqtt-public-sit.faw.cn:8883";  //传入null，即使用腾讯云物联网通信默认地址 "${ProductId}.iotcloud.tencentdevices.com:8883"  https://cloud.tencent.com/document/product/634/32546
    private String mBrokerURL = "ssl://fawtsp-mqtt-sit.faw.cn:8883";
    private String mProductID = "XN03IY1B4J";
    private String mDevName = "app_test";
    private String mDevPSK  = "QVuXmEVWLERWWWEegO0Fzw=="; //若使用证书验证，设为null
    private String mTestTopic = "XN03IY1B4J/app_test/data";


    /*真车配置*/
//    private String mBrokerURL = "ssl://fawtsp-mqtt-public-sit.faw.cn:8883";  //传入null，即使用腾讯云物联网通信默认地址 "${ProductId}.iotcloud.tencentdevices.com:8883"  https://cloud.tencent.com/document/product/634/32546
////    private String mBrokerURL = "ssl://10.112.16.22:8883";  //传入null，即使用腾讯云物联网通信默认地址 "${ProductId}.iotcloud.tencentdevices.com:8883"  https://cloud.tencent.com/document/product/634/32546
//
//    private String mProductID = "6WYMRTCPAM";
//    private String mDevName = "app_real";
//    private String mDevPSK  = "nrRI5+fuV1AczfwxAofd7Q=="; //若使用证书验证，设为null
//    private String mTestTopic = "6WYMRTCPAM/app_real/data";    // productID/DeviceName/TopicName

    private String mSubProductID = ""; // If you wont test gateway, let this to be null
    private String mSubDevName = "";
    private String mSubDevPsk = "BuildConfig.SUB_DEVICE_PSK";
    private String mDevCertName = "YOUR_DEVICE_NAME_cert.crt";
    private String mDevKeyName  = "YOUR_DEVICE_NAME_private.key";
    private String mProductKey = "BuildConfig.PRODUCT_KEY";        // Used for dynamic register
    private String mDevCert = "";           // Cert String
    private String mDevPriv = "";           // Priv String

    private volatile boolean mIsConnected=false;
    ScalableImageView sImgView ;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        this.mContext = this;
        this.mActivity = this;
        rootView = getLayoutInflater().from(this).inflate(R.layout.activity_remote_control, null);

        setContentView(rootView);

        mqttSample= new MQTTSample(getApplication(), new SelfMqttActionCallBack(), mBrokerURL, mProductID, mDevName, mDevPSK,
                mDevCert, mDevPriv, mSubProductID, mSubDevName, mTestTopic, null, null, true, new SelfMqttLogCallBack());


                while (!mIsConnected&&connectMQTTTimes<5) {
                    Log.d(TAG, "onCreate: Connecting Mqtt");
                    //轮询连接,万分感谢陈岩大佬
                    Log.d(TAG, "onCreate: mqttSample"+mqttSample.toString());
                    mqttSample.connect();
                    mqttSample.subscribeTopic();
                    Log.d(TAG, "onCreate: Connet times:"+connectMQTTTimes++);
                    sleep(1000);
                }
                if(mIsConnected){
                    Toast.makeText(RemoteControl.this, "连接成功",Toast.LENGTH_SHORT).show();
                }else {
                    Log.d(TAG, "onCreate: 连接失败");
//                    Toast.makeText(RemoteControl.this, "连接失败",Toast.LENGTH_SHORT).show();
//                    finishActivity(1);
                    this.finish();
                }




        shiftHandbrake(1);
        Log.d(TAG, "onCreate: ");

        new Thread(new Runnable() {
            @Override
            public void run() {
                 while (true) {
                     if(braking){
                    try {
                        if (active_braking){
                            moveVehicle(-1.0,0.0,wheelAngle);
                            sleep(50);
                        }
                        else {
                        moveVehicle(-0.2,0.0,wheelAngle);}
                        sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }}
            }
        }}).start();

        /**常亮*/
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();

        CompentOnTouch compentOnTouch = new CompentOnTouch();

        //返回键
        findViewById(R.id.back2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                //对话框
                View my_view = LayoutInflater.from(RemoteControl.this).inflate(R.layout.my_dialog,null,false);
                final AlertDialog dialog = new AlertDialog.Builder(RemoteControl.this).setView(my_view).create();
                TextView Title = my_view.findViewById(R.id.title);
                TextView Context = my_view.findViewById(R.id.content);
                Title.setText("结束挪车");
                Context.setText("您已确定车辆已经抵达目标位置并结束挪车操作吗？");
                ImageButton Confirm = my_view.findViewById(R.id.confirm);
                ImageButton cancel = my_view.findViewById(R.id.cancel);
                Confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        Intent intent=new Intent(RemoteControl.this, MainActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(RemoteControl.this, "取消成功",Toast.LENGTH_SHORT).show();
                        player.startPlay();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                player.onPause();
                dialog.getWindow().setLayout(1000,600);
            }
        });

        imageButton_forward=findViewById(R.id.forward);
        imageButton_forward.setOnTouchListener(compentOnTouch);

        imageButton_backward=findViewById(R.id.backward);
        imageButton_backward.setOnTouchListener(compentOnTouch);

        imageButton_brake=findViewById(R.id.brake);
        imageButton_brake.setOnTouchListener(compentOnTouch);
        sImgView = findViewById(R.id.steering_wheel);

        //方向盘角度在速度处显示
        countDownTimer=new CountDownTimer(100000000,200) {
            @Override
            public void onTick(long millisUntilFinished) {
                wheelAngle = sImgView.getmDegree();
                Speed = findViewById(R.id.speed);
                Speed.setText(String.valueOf((int)wheelAngle));
            }
            @Override
            public void onFinish() {
            }
        }.start();

        //下拉单选按钮
        Video_Modul_Spinner = (Spinner)findViewById(R.id.Spinner_VIdeo_Model);
        Video_Modul_Spinner.setSelection(0);//进入不会自动播放
        Video_Modul_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String result = parent.getItemAtPosition(position).toString();
                    Toast.makeText(RemoteControl.this, result, Toast.LENGTH_SHORT).show();
                    switch (position) {
                        case 0: {
                            /**前摄像*/
                            list = new ArrayList<VideoijkBean>();
                            String url1 = "rtmp://150.158.176.170/live/test_vin_1";
                            String url2 = "rtmp://150.158.176.170/live/test_vin_2";
                            VideoijkBean m1 = new VideoijkBean();
                            m1.setStream("原始视频");
                            m1.setUrl(url1);
                            VideoijkBean m2 = new VideoijkBean();
                            m2.setStream("融合视频");
                            m2.setUrl(url2);
                            list.add(m1);
                            list.add(m2);
                            player = new PlayerView(mActivity, rootView)
                                    .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                                    .setScaleType(PlayStateParams.fillparent) //视频界面剪裁设置
                                    .forbidTouch(false)
                                    .hideSteam(false)
                                    .hideMenu(true)
                                    .hideCenterPlayer(true)
                                    .hideBack(false)
                                    .setOnlyFullScreen(true)
                                    .setNetWorkTypeTie(false)
                                    .hideRotation(true)
                                    .hideFullscreen(true)
                                    .hideBack(true)
                                    .setChargeTie(true, 480)//设置最长播放时间
                                    .showThumbnail(new OnShowThumbnailListener() {
                                        @Override
                                        public void onShowThumbnail(ImageView ivThumbnail) {
//                                 加载前显示的缩略图
                                            Glide.with(mContext)
                                                    .load(R.drawable.pic_before_video)
                                                    .placeholder(R.drawable.pic_before_video) //加载成功之前占位图
                                                    .error(R.color.cl_error)//加载错误之后的错误图
                                                    .into(ivThumbnail);
                                        }
                                    })
                                    .setPlayerStartOrPauseListener(new OnPlayerStartOrPauseListener() {
                                        @Override
                                        public void onStartOrPause() {
                                            //对话框
                                            AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                                            builder.setTitle("暂停挪车");//设置对话框的标题
                                            builder.setMessage("挪车已暂停，是否继续挪车？");//设置对话框的内容
                                            builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {  //这个是设置确定按钮
                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {
                                                    player.startPlay();
                                                }
                                            });
                                            builder.setNegativeButton("结束", new DialogInterface.OnClickListener() {  //取消按钮

                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {
                                                    finish();
                                                    Intent intent=new Intent(RemoteControl.this,MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                            AlertDialog b=builder.create();
                                            b.show();
                                        }
                                    })
                                    .setPlaySource(list)
                                    .startPlay();
                        }
                        break;

                        case 1: {
                            /**后摄像*/
                            String url3 = "rtmp://150.158.176.170/live/test_vin_2";
                            playVideoUrl(url3);
                        }
                        break;

                        case 2: {
                            /**左摄像*/
                            String url4 = "rtmp://150.158.176.170/live/test_vin_3";
                            playVideoUrl(url4);
                        }
                        break;

                        case 3: {
                            /**右摄像*/
                            String url5 = "rtmp://150.158.176.170/live/test_vin_4";
                            playVideoUrl(url5);
                        }
                        break;

                        case 4: {
                            /**上帝*/
                            String url6 = "rtmp://150.158.176.170/live/test_vin_5";
                            playVideoUrl(url6);
                        }
                        break;

                        default:
                            break;
                    }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }

    //后四个视频播放器播放
    public void playVideoUrl( String url){
        player = new PlayerView(mActivity, rootView)
                .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                .setScaleType(PlayStateParams.fillparent) //视频界面剪裁设置
                .forbidTouch(false)
                .hideSteam(true)
                .hideMenu(true)
                .hideCenterPlayer(true)
                .hideBack(false)
                .setOnlyFullScreen(true)
                .setNetWorkTypeTie(false)
                .hideRotation(true)
                .hideFullscreen(true)
                .hideBack(true)
                .setChargeTie(true, 480)//设置最长播放时间
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
//                                 加载前显示的缩略图
                        Glide.with(mContext)
                                .load(R.drawable.pic_before_video)
                                .placeholder(R.drawable.pic_before_video) //加载成功之前占位图
                                .error(R.color.cl_error)//加载错误之后的错误图
                                .into(ivThumbnail);
                    }
                })
                .setPlayerStartOrPauseListener(new OnPlayerStartOrPauseListener() {
                    @Override
                    public void onStartOrPause() {
                        //对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("暂停挪车");//设置对话框的标题
                        builder.setMessage("挪车已暂停，是否继续挪车？");//设置对话框的内容
                        builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {  //这个是设置确定按钮
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                player.startPlay();
                            }
                        });
                        builder.setNegativeButton("结束", new DialogInterface.OnClickListener() {  //取消按钮

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                finish();
                                Intent intent = new Intent(RemoteControl.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();
                    }
                })
                .setPlaySource(url)
                .startPlay();
    }


    /**
    1表示手刹释放，0表示手刹锁定
     **/

    private void shiftHandbrake(int handbrakeToSet) {
        if(handbrakeToSet!=handBrakeStatus){
            for(int i=0;i<15;i++){
                Handbrake mHandbrake = new Handbrake();
                mHandbrake.setTimestamp(ntpTime.getNtpTime());
                mHandbrake.setStatus(handbrakeToSet);
                mHandbrake.setType(14);
                mHandbrake.setTaskid("6D");
                // 需先在腾讯云控制台，增加自定义主题: data，用于更新自定义数据
                mqttSample.publishTopic("data", JSON.toJSONString(mHandbrake));
                Log.d(TAG, "onClick: 手刹"+JSON.toJSONString(mHandbrake));
                sleep(50);
            }
            handBrakeStatus=handbrakeToSet;}
        else return;
    }

    /**
    1,2,3,4分別對應P,R,N,D四個檔位
     **/
    private void shiftGear(final int gear){
//        if(speed==0){
        for(int i=0;i<10;i++){
                Gears mGear = new Gears();
                mGear.setTimestamp(ntpTime.getNtpTime());
                Log.d(TAG, "onClick: "+System.currentTimeMillis());
                mGear.setGear(gear);
                mGear.setType(13);
                mGear.setTaskid("手機挂"+gear+"档");
                // 需先在腾讯云控制台，增加自定义主题: data，用于更新自定义数据
                mqttSample.publishTopic("data", JSON.toJSONString(mGear));
                Log.d(TAG, "onClick: "+JSON.toJSONString(mGear));
                sleep(50);
        }
        gearGlobal=gear;
//    }
//        else {
//            moveVehicle(-0.3,0.0,0.0);
//            shiftGear(gear);
//        }
    }

    /**

     @videoType
     1为前方原始视频流，2为后方原始视频流，3为左侧原始视频流，4为右侧原始视频流，5为感知融合视频流，6为上帝视角视频流
     @videoStatus
     1为打开，0为关闭
     */
    private void shiftVideoType(int videoType,int videoStatus){
        Video mVideo = new Video();
        mVideo.setTimestamp(ntpTime.getNtpTime());
        mVideo.setVideo_type(videoType);
        mVideo.setOperation(videoStatus);
        mVideo.setType(12);
        // 需先在腾讯云控制台，增加自定义主题: data，用于更新自定义数据
        mqttSample.publishTopic("data", JSON.toJSONString(mVideo));
        Log.d(TAG, "onClick: "+JSON.toJSONString(mVideo));
    }

    /**
     * @acceleration 加速度，正是加速，負的是減速
     * @speed 目標速度
     * @wheelAngle 方向盤轉角
     */
    private void moveVehicle(Double acceleration,Double speed,Double wheelAngle){
        Control mMove = new Control();
        mMove.setTimestamp(ntpTime.getNtpTime());
        mMove.setAcceleration(acceleration);
        mMove.setSpeed(speed);
        mMove.setType(11);
        mMove.setWheel_angle(wheelAngle);
        // 需先在腾讯云控制台，增加自定义主题: data，用于更新自定义数据
        mqttSample.publishTopic("data", JSON.toJSONString(mMove));
        Log.d(TAG, "onClick: 上传指令"+JSON.toJSONString(mMove));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    //ms为需要休眠的时长
    public static void sleep(long ms)
    {
        //uptimeMillis() Returns milliseconds since boot, not counting time spent in deep sleep.
        long start = uptimeMillis();
        long duration = ms;
        boolean interrupted = false;
        do {
            try {
                Thread.sleep(duration);
            }
            catch (InterruptedException e) {
                interrupted = true;
            }
            duration = start + ms - uptimeMillis();
        } while (duration > 0);

        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 实现TXMqttActionCallBack回调接口
     */
    private class SelfMqttActionCallBack extends TXMqttActionCallBack {

        @Override
        public void onConnectCompleted(Status status, boolean reconnect, Object userContext, String msg) {
            String userContextInfo = "";
            if (userContext instanceof MQTTRequest) {
                userContextInfo = userContext.toString();
            }
            String logInfo = String.format("onConnectCompleted, status[%s], reconnect[%b], userContext[%s], msg[%s]",
                    status.name(), reconnect, userContextInfo, msg);
            Log.d(TAG, "onConnectCompleted: "+logInfo);
            if(status==Status.OK){
             mIsConnected = true;}
        }

        @Override
        public void onConnectionLost(Throwable cause) {
            String logInfo = String.format("onConnectionLost, cause[%s]", cause.toString());
            Log.d(TAG, "onConnectCompleted: "+logInfo);
        }

        @Override
        public void onDisconnectCompleted(Status status, Object userContext, String msg) {
            String userContextInfo = "";
            if (userContext instanceof MQTTRequest) {
                userContextInfo = userContext.toString();
            }
            String logInfo = String.format("onDisconnectCompleted, status[%s], userContext[%s], msg[%s]", status.name(), userContextInfo, msg);
            Log.d(TAG, "onConnectCompleted: "+logInfo);
            mIsConnected = false;
        }

        @Override
        public void onPublishCompleted(Status status, IMqttToken token, Object userContext, String errMsg) {
            String userContextInfo = "";
            if (userContext instanceof MQTTRequest) {
                userContextInfo = userContext.toString();
            }
            String logInfo = String.format("onPublishCompleted, status[%s], topics[%s],  userContext[%s], errMsg[%s]",
                    status.name(), Arrays.toString(token.getTopics()), userContextInfo, errMsg);
            Log.d(TAG, "onConnectCompleted: "+logInfo);
        }

        @Override
        public void onSubscribeCompleted(Status status, IMqttToken asyncActionToken, Object userContext, String errMsg) {
            String userContextInfo = "";
            if (userContext instanceof MQTTRequest) {
                userContextInfo = userContext.toString();
            }
            String logInfo = String.format("onSubscribeCompleted, status[%s], topics[%s], userContext[%s], errMsg[%s]",
                    status.name(), Arrays.toString(asyncActionToken.getTopics()), userContextInfo, errMsg);
//            Toast.makeText(RemoteControl.this, logInfo,Toast.LENGTH_SHORT).show();
            if (Status.ERROR == status) {
                Log.d(TAG, "onConnectCompleted: "+logInfo);
            } else {
                Log.d(TAG, "onConnectCompleted: "+logInfo);
            }
        }

        @Override
        public void onUnSubscribeCompleted(Status status, IMqttToken asyncActionToken, Object userContext, String errMsg) {
            String userContextInfo = "";
            if (userContext instanceof MQTTRequest) {
                userContextInfo = userContext.toString();
            }
            String logInfo = String.format("onUnSubscribeCompleted, status[%s], topics[%s], userContext[%s], errMsg[%s]",
                    status.name(), Arrays.toString(asyncActionToken.getTopics()), userContextInfo, errMsg);
            Log.d(TAG, "onConnectCompleted: "+logInfo);
        }

        @Override
        public void onMessageReceived(final String topic, final MqttMessage message) {
            String logInfo = String.format("receive command, topic[%s], message[%s]", topic, message.toString());
//            Toast.makeText(RemoteControl.this, logInfo,Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onConnectCompleted: "+logInfo);
        }
    }

    /**
     * 实现TXMqttLogCallBack回调接口
     */
    private class SelfMqttLogCallBack extends TXMqttLogCallBack {

        @Override
        public String setSecretKey() {
            String secertKey;
            if (mDevPSK != null && mDevPSK.length() != 0) {  //密钥认证
                secertKey = mDevPSK;
                secertKey = secertKey.length() > 24 ? secertKey.substring(0,24) : secertKey;
                return secertKey;
            } else {
                BufferedReader cert;

                if (mDevCert != null && mDevCert.length() != 0) { //动态注册,从DevCert中读取
                    cert = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(mDevCert.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));

                } else { //证书认证，从证书文件中读取
                    AssetManager assetManager = mParent.getAssets();
                    if (assetManager == null) {
                        return null;
                    }
                    try {
                        cert=new BufferedReader(new InputStreamReader(assetManager.open(mDevCertName)));
                    } catch (IOException e) {
//                        mParent.printLogInfo(TAG, "getSecertKey failed, cannot open CRT Files.",mLogInfoText);
                        return null;
                    }
                }
                //获取密钥
                try {
                    if (cert.readLine().contains("-----BEGIN")) {
                        secertKey = cert.readLine();
                        secertKey = secertKey.length() > 24 ? secertKey.substring(0,24) : secertKey;
                    } else {
                        secertKey = null;
//                        mParent.printLogInfo(TAG,"Invaild CRT Files.", mLogInfoText);
                    }
                    cert.close();
                } catch (IOException e) {
                    TXLog.e(TAG, "getSecertKey failed.", e);
//                    mParent.printLogInfo(TAG,"getSecertKey failed.", mLogInfoText);
                    return null;
                }
            }

            return secertKey;
        }

        @Override
        public void printDebug(String message){
//            mParent.printLogInfo(TAG, message, mLogInfoText);
            //TXLog.d(TAG,message);
        }

        @Override
        public boolean saveLogOffline(String log){
            //判断SD卡是否可用
            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//                mParent.printLogInfo(TAG, "saveLogOffline not ready", mLogInfoText);
                return false;
            }

            String logFilePath = mLogPath + mProductID + mDevName + ".log";

            TXLog.i(TAG, "Save log to %s", logFilePath);

            try {
                BufferedWriter wLog = new BufferedWriter(new FileWriter(new File(logFilePath), true));
                wLog.write(log);
                wLog.flush();
                wLog.close();
                return true;
            } catch (IOException e) {
                String logInfo = String.format("Save log to [%s] failed, check the Storage permission!", logFilePath);
//                mParent.printLogInfo(TAG,logInfo, mLogInfoText);
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public String readOfflineLog(){
            //判断SD卡是否可用
            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//                mParent.printLogInfo(TAG, "readOfflineLog not ready", mLogInfoText);
                return null;
            }

            String logFilePath = mLogPath + mProductID + mDevName + ".log";

            TXLog.i(TAG, "Read log from %s", logFilePath);

            try {
                BufferedReader logReader = new BufferedReader(new FileReader(logFilePath));
                StringBuilder offlineLog = new StringBuilder();
                int data;
                while (( data = logReader.read()) != -1 ) {
                    offlineLog.append((char)data);
                }
                logReader.close();
                return offlineLog.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public boolean delOfflineLog(){

            //判断SD卡是否可用
            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//                mParent.printLogInfo(TAG, "delOfflineLog not ready", mLogInfoText);
                return false;
            }

            String logFilePath = mLogPath + mProductID + mDevName + ".log";

            File file = new File(logFilePath);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    return true;
                }
            }
            return false;
        }

    }

    class
    CompentOnTouch implements View.OnTouchListener {

        public boolean isOnLongClick=false;
        int i = 0;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (view.getId()) {
// 这是btnMius下的一个层，为了增强易点击性
                case R.id.backward:
                    onTouchChange("backward", motionEvent.getAction());
                    break;
// 这里也写，是为了增强易点击性
                case R.id.forward:
                    onTouchChange("forward", motionEvent.getAction());
                    break;
                case R.id.brake:
                    onTouchChange("brake", motionEvent.getAction());
                    break;
            }
            return true;
        }

        private void onTouchChange(String methodName, int eventAction) {
            Log.d(TAG, "onTouchChange: "+methodName+eventAction);
            if("backward".equals(methodName)||"forward".equals(methodName)){
            braking =false;
            active_braking=false;}
// 按下松开分别对应启动停止前进方法
            if ("backward".equals(methodName)) {
//                Video_Modul_Spinner.setSelection(1);
                if(gearGlobal!=2){ shiftGear(2);}
                MiusThread miusThread = null;
                if (eventAction == MotionEvent.ACTION_DOWN) {
                    miusThread = new MiusThread();
                    isOnLongClick = true;
                    miusThread.start();
                } else if (eventAction == MotionEvent.ACTION_UP) {
                    isOnLongClick=false;
                    braking =true;
                    Log.d(TAG, "onTouchChange: 松开手指"+ braking);
//                    Log.d(TAG, "onTouchChange: isOnLongClick=false"+miusThread.isInterrupted());
                    if (miusThread != null) {
                        miusThread.interrupt();
                        Log.d(TAG, "onTouchChange: 终止线程");
                        isOnLongClick = false;
                    }
                } else if (eventAction == MotionEvent.ACTION_MOVE) {
                    if (miusThread != null) {
                        isOnLongClick = true;
                    }
                }
            }
// 按下松开分别对应启动停止加线程方法
            else if ("forward".equals(methodName)) {
//                Video_Modul_Spinner.setSelection(0);
                if(gearGlobal!=4){ shiftGear(4);}
                PlusThread plusThread = null;
                if (eventAction == MotionEvent.ACTION_DOWN) {
                    plusThread = new PlusThread();
                    isOnLongClick = true;
                    plusThread.start();
                } else if (eventAction == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "onTouchChange: 松开手指");
                    isOnLongClick=false;
                    braking =true;
                    if (plusThread != null) {
                        isOnLongClick = false;
                    }
                } else if (eventAction == MotionEvent.ACTION_MOVE) {
                    if (plusThread != null) {
                        isOnLongClick = true;
                    }
                }
            }
            else if ("brake".equals(methodName)) {
                braking=true;
                if (eventAction == MotionEvent.ACTION_DOWN) {
                    active_braking=true;
//                    moveVehicle(-1.0,0.0,0.0);
                    isOnLongClick = true;
                } else if (eventAction == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "onTouchChange: 松开手指");
                    isOnLongClick=false;
                    active_braking=false;
                } else if (eventAction == MotionEvent.ACTION_MOVE) {
                    isOnLongClick = true;
                    active_braking=true;
//                    moveVehicle(-1.0,0.0,0.0);
                }
            }
        }


        // 后退操作
        class MiusThread extends Thread {
            @Override
            public void run() {
                while (isOnLongClick) {
                    try {
                        Thread.sleep(50);
                        myHandler.sendEmptyMessage(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    super.run();
                }
//                while (!isOnLongClick) {
//                    try {
//                        Thread.sleep(100);
//                        myHandler.sendEmptyMessage(3);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    super.run();
//                }
            }
        }


        // 前进操作
        class PlusThread extends Thread {
            @Override
            public void run() {
                while (isOnLongClick) {
                    try {
                        Thread.sleep(40);
                        myHandler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    super.run();
                }
//                while (!isOnLongClick) {
//                    try {
//                        Thread.sleep(100);
//                        myHandler.sendEmptyMessage(3);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    super.run();
//                }
            }
        }

        Handler myHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        //前进操作
                        Control mForward = new Control(5.0,0.2,wheelAngle);
                        mqttSample.publishTopic("data", JSON.toJSONString(mForward));
                        Log.d(TAG, "第 "+(i++)+"次上传\n"+JSON.toJSONString(mForward));
                        moveVehicle(0.2,5.0,wheelAngle);
                        break;
                    case 2:
                        Control mBackward = new Control(-5.0,0.2,wheelAngle);
                        mqttSample.publishTopic("data", JSON.toJSONString(mBackward));
                        Log.d(TAG, "第 "+(i++)+"次上传\n"+JSON.toJSONString(mBackward));
                        moveVehicle(0.1,-5.0,wheelAngle);
                        break;
                    case 3:
                        Control mBreak = new Control(0.0,-0.2,wheelAngle);
                        mqttSample.publishTopic("data", JSON.toJSONString(mBreak));
                        Log.d(TAG, "第 "+(i++)+"次上传\n"+JSON.toJSONString(mBreak));
                        break;
                }
            };
        };
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
        if(mIsConnected){
        super.onDestroy();
        if (player != null) {
            for(int i=1;i<7;i++){
            shiftVideoType(i,0);}
            player.onDestroy();
        }
        if(speed!=0){moveVehicle(-0.3,0.0,0.0);}
        if(braking){braking=false;}
        if(gearGlobal!=1){shiftGear(1);}
        if(handBrakeStatus==1){shiftHandbrake(0);}
        if(mIsConnected){mqttSample.disconnect();}

        Log.d(TAG, "onDestroy: 斷開視頻以及MQTT連接");}
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
//        super.onBackPressed();
        //对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setTitle("结束挪车");//设置对话框的标题
        builder.setMessage("您已确定车辆已经抵达目标位置并结束挪车操作吗？");//设置对话框的内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //这个是设置确定按钮
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
                Intent intent=new Intent(RemoteControl.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(RemoteControl.this, "取消成功",Toast.LENGTH_SHORT).show();
                player.startPlay();
            }
        });
        AlertDialog b=builder.create();
        b.show();
        player.onPause();
        /**demo的内容，恢复设备亮度状态*/
        if (wakeLock != null) {
            wakeLock.release();
        }
    }
}
