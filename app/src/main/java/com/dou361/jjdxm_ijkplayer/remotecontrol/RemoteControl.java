package com.dou361.jjdxm_ijkplayer.remotecontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.dou361.jjdxm_ijkplayer.videomonitoring.VideoMonitor;
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
    private int gearGlobal=0;
    private int flagget = 1;
    private Button LlightingButton;
    private ImageButton imageButton_forward,imageButton_backward;
    private ImageView app_video_play;
    private Spinner Video_Modul_Spinner;

    private double wheelAngle=0.0;
    private double speed=0.0;


    private String mBrokerURL = "ssl://fawtsp-mqtt-public-dev.faw.cn:8883";  //传入null，即使用腾讯云物联网通信默认地址 "${ProductId}.iotcloud.tencentdevices.com:8883"  https://cloud.tencent.com/document/product/634/32546
   /* private String mProductID = "2N8PWJAI0V";
    private String mDevName = "OPPOA57t";
    private String mDevPSK  = "TbtnFhJDmRe7N41vDBRVtA=="; //若使用证书验证，设为null
    private String mTestTopic = "2N8PWJAI0V/OPPOA57t/data";  */  // productID/DeviceName/TopicName

    /*真车配置*/
    private String mProductID = "KM8UZXZOV9";
    private String mDevName = "android_test";
    private String mDevPSK  = "+xRWqTlp0UPbwSKXVgiNxA=="; //若使用证书验证，设为null
    private String mTestTopic = "KM8UZXZOV9/android_test/data";    // productID/DeviceName/TopicName
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

        while (!mIsConnected) {
            Log.d(TAG, "onCreate: Connecting Mqtt");
            //轮询连接,万分感谢陈岩大佬
            mqttSample= new MQTTSample(getApplication(), new SelfMqttActionCallBack(), mBrokerURL, mProductID, mDevName, mDevPSK,
                    mDevCert, mDevPriv, mSubProductID, mSubDevName, mTestTopic, null, null, true, new SelfMqttLogCallBack());
            Log.d(TAG, "onCreate: mqttSample"+mqttSample.toString());
            mqttSample.connect();
            sleep(2000);}


        shiftHandbrake(1);
        Log.d(TAG, "onCreate: ");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 刹车状态:"+ braking);
                 while (true) {
                     if(braking){
                    try {
                        moveVehicle(-0.1,0.0,wheelAngle);
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

        imageButton_forward=findViewById(R.id.forward);
        imageButton_forward.setOnTouchListener(compentOnTouch);

        imageButton_backward=findViewById(R.id.backward);
        imageButton_backward.setOnTouchListener(compentOnTouch);
        sImgView = findViewById(R.id.steering_wheel);

        //方向盘角度在速度处显示
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    wheelAngle = sImgView.getmDegree();
                }
            }
        }).start();

        countDownTimer=new CountDownTimer(100000,200) {
            @Override
            public void onTick(long millisUntilFinished) {
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
                            String url1 = "rtmp://202.69.69.180:443/webcast/bshdlive-pc";
                            String url2 = "http://ivi.bupt.edu.cn/hls/cctv1.m3u8";
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
                                    .hideMenu(false)
                                    .hideCenterPlayer(true)
                                    .hideBack(false)
                                    .setOnlyFullScreen(true)
                                    .setNetWorkTypeTie(false)
                                    .hideRotation(true)
                                    .hideFullscreen(true)
                                    .setChargeTie(true, 480)//设置最长播放时间
                                    .showThumbnail(new OnShowThumbnailListener() {
                                        @Override
                                        public void onShowThumbnail(ImageView ivThumbnail) {
//                                 加载前显示的缩略图
                                            Glide.with(mContext)
                                                    .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                                                    .placeholder(R.color.cl_default) //加载成功之前占位图
                                                    .error(R.color.cl_error)//加载错误之后的错误图
                                                    .into(ivThumbnail);
                                        }
                                    })
                                    .setPlayerBackListener(new OnPlayerBackListener() {
                            @Override
                            public void onPlayerBack() {
                                //对话框
                                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                                builder.setIcon(R.drawable.shangbackground);
                                builder.setTitle("结束挪车");//设置对话框的标题
                                builder.setMessage("您已确定车辆已经抵达目标位置并结束挪车操作吗？");//设置对话框的内容
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //这个是设置确定按钮
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        finish();
                                        Intent intent=new Intent(RemoteControl.this, VideoMonitor.class);
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

                            }
                        })
                                    .setPlayerStartOrPauseListener(new OnPlayerStartOrPauseListener() {
                                        @Override
                                        public void onStartOrPause() {
                                            //对话框
                                            AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                                            builder.setIcon(R.drawable.shangbackground);
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
                                                    Intent intent=new Intent(RemoteControl.this,VideoMonitor.class);
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
                            String url3 = "http://ivi.bupt.edu.cn/hls/cctv2.m3u8";
                            playVideoUrl(url3);
                        }
                        break;

                        case 2: {
                            /**左摄像*/
                            String url4 = "http://ivi.bupt.edu.cn/hls/cctv3.m3u8";
                            playVideoUrl(url4);
                        }
                        break;

                        case 3: {
                            /**右摄像*/
                            String url5 = "http://ivi.bupt.edu.cn/hls/cctv4.m3u8";
                            playVideoUrl(url5);

                        }
                        break;

                        case 4: {
                            /**上帝*/
                            String url6 = "http://ivi.bupt.edu.cn/hls/cctv13.m3u8";
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
    public  void playVideoUrl( String url){
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
                .setChargeTie(true, 480)//设置最长播放时间
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
//                                 加载前显示的缩略图
                        Glide.with(mContext)
                                .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                                .placeholder(R.color.cl_default) //加载成功之前占位图
                                .error(R.color.cl_error)//加载错误之后的错误图
                                .into(ivThumbnail);
                    }
                })
                .setPlayerBackListener(new OnPlayerBackListener() {
                    @Override
                    public void onPlayerBack() {
                        //对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setIcon(R.drawable.shangbackground);
                        builder.setTitle("结束挪车");//设置对话框的标题
                        builder.setMessage("您已确定车辆已经抵达目标位置并结束挪车操作吗？");//设置对话框的内容
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //这个是设置确定按钮
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                finish();
                                Intent intent = new Intent(RemoteControl.this, VideoMonitor.class);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(RemoteControl.this, "取消成功", Toast.LENGTH_SHORT).show();
                                player.startPlay();
                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();
                        player.onPause();
                    }
                })
                .setPlayerStartOrPauseListener(new OnPlayerStartOrPauseListener() {
                    @Override
                    public void onStartOrPause() {
                        //对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setIcon(R.drawable.shangbackground);
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
                                Intent intent = new Intent(RemoteControl.this, VideoMonitor.class);
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

    private void shiftHandbrake(int handbrakeStatus) {
        Handbrake  mHandbrake = new Handbrake();
        mHandbrake.setTimestamp(System.currentTimeMillis());
        mHandbrake.setStatus(handbrakeStatus);
        mHandbrake.setType(14);
        mHandbrake.setTaskid("6D");
        // 需先在腾讯云控制台，增加自定义主题: data，用于更新自定义数据
        mqttSample.publishTopic("data", JSON.toJSONString(mHandbrake));
        Log.d(TAG, "onClick: 手刹"+JSON.toJSONString(mHandbrake));
    }

    /**
    1,2,3,4分別對應P,R,N,D四個檔位
     **/
    private void shiftGear(final int gear){
        for(int i=0;i<10;i++){
                Gears mGear = new Gears();
                mGear.setTimestamp(System.currentTimeMillis());
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
    }

    /**

     @videoType
     1为前方原始视频流，2为后方原始视频流，3为左侧原始视频流，4为右侧原始视频流，5为感知融合视频流，6为上帝视角视频流
     @videoStatus
     1为打开，0为关闭
     */
    private void shiftVideoType(int videoType,int videoStatus){
        Video mVideo = new Video();
        mVideo.setTimestamp(System.currentTimeMillis());
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
        mMove.setTimestamp(System.currentTimeMillis());
        mMove.setAcceleration(acceleration);
        mMove.setSpeed(speed);
        mMove.setType(11);
        mMove.setWheel_angle(wheelAngle);
        // 需先在腾讯云控制台，增加自定义主题: data，用于更新自定义数据
        mqttSample.publishTopic("data", JSON.toJSONString(mMove));
        Log.d(TAG, "onClick: 上传刹车"+JSON.toJSONString(mMove));
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
            }
            return true;
        }

        private void onTouchChange(String methodName, int eventAction) {
            Log.d(TAG, "onTouchChange: "+methodName+eventAction);
            braking =false;
// 按下松开分别对应启动停止前进方法
            if ("backward".equals(methodName)) {
                if(gearGlobal!=2){
                shiftGear(2);}
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
                if(gearGlobal!=4){
                shiftGear(4);}
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
        }


        // 后退操作
        class MiusThread extends Thread {
            @Override
            public void run() {
                while (isOnLongClick) {
                    try {
                        Thread.sleep(40);
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
//                        Control mForward = new Control(5.0,0.1,wheelAngle);
//                        mqttSample.publishTopic("data", JSON.toJSONString(mForward));
//                        Log.d(TAG, "第 "+(i++)+"次上传\n"+JSON.toJSONString(mForward));
                        moveVehicle(0.1,5.0,wheelAngle);
                        break;
                    case 2:
//                        Control mBackward = new Control(-5.0,0.1,wheelAngle);
//                        mqttSample.publishTopic("data", JSON.toJSONString(mBackward));
//                        Log.d(TAG, "第 "+(i++)+"次上传\n"+JSON.toJSONString(mBackward));
                        moveVehicle(0.1,-5.0,wheelAngle);
                        Log.d("TAG","Backward:"+i--);
                        break;
                    case 3:
                        Control mBreak = new Control(0.0,-0.1,wheelAngle);
                        mqttSample.publishTopic("data", JSON.toJSONString(mBreak));
                        Log.d(TAG, "第 "+(i++)+"次上传\n"+JSON.toJSONString(mBreak));
                        break;
                }
            };
        };
    }
}
