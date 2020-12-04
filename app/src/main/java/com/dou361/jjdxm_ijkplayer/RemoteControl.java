package com.dou361.jjdxm_ijkplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnPlayerBackListener;
import com.dou361.ijkplayer.listener.OnPlayerStartOrPauseListener;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.dou361.jjdxm_ijkplayer.command.Handbrake;
import com.dou361.jjdxm_ijkplayer.mqtt.MQTTRequest;
import com.dou361.jjdxm_ijkplayer.mqtt.MQTTSample;
import com.tencent.iot.hub.device.android.core.log.TXMqttLogCallBack;
import com.tencent.iot.hub.device.android.core.util.TXLog;
import com.tencent.iot.hub.device.java.core.common.Status;
import com.tencent.iot.hub.device.java.core.mqtt.TXMqttActionCallBack;
import com.tencent.iot.hub.device.java.main.shadow.SelfMqttActionCallBack;


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


//I added a line here by Github
//I added this line by Android Studio

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) by style.xml.
 */
public class RemoteControl extends Activity {

    private final static String mLogPath = Environment.getExternalStorageDirectory().getPath() + "/tencent/";

    private MainActivity mParent;
   // private MQTTLocalSample mqttLocalSample;
    private MQTTSample mqttSample;

    private PlayerView player;
    private Context mContext;
    private List<VideoijkBean> list;
    private PowerManager.WakeLock wakeLock;
    private View rootView;
    private Activity mActivity;

    private static final String TAG = "FullscreenActivity";
    private boolean Flag = false;
    private int flagmove = 1;
    private int flagget = 1;
    private Button LlightingButton;
    private ImageButton imageButton_forward,imageButton_backward;
    private ImageView app_video_play;
    private Spinner Video_Modul_Spinner;

    private String mBrokerURL = "ssl://fawtsp-mqtt-public-dev.faw.cn:8883";  //传入null，即使用腾讯云物联网通信默认地址 "${ProductId}.iotcloud.tencentdevices.com:8883"  https://cloud.tencent.com/document/product/634/32546
    /*private String mProductID = "2N8PWJAI0V";
    private String mDevName = "OPPOA57t";
    private String mDevPSK  = "TbtnFhJDmRe7N41vDBRVtA=="; //若使用证书验证，设为null
    private String mTestTopic = "2N8PWJAI0V/OPPOA57t/data";    // productID/DeviceName/TopicName
*/
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

    private final static String BROKER_URL = "broker_url";
    private final static String PRODUCT_ID = "product_id";
    private final static String DEVICE_NAME = "dev_name";
    private final static String DEVICE_PSK = "dev_psk";
    private final static String SUB_PRODUCID = "sub_prodid";
    private final static String SUB_DEVNAME = "sub_devname";
    private final static String TEST_TOPIC  = "test_topic";

    private final static String DEVICE_CERT = "dev_cert";
    private final static String DEVICE_PRIV  = "dev_priv";
    private final static String PRODUCT_KEY  = "product_key";
    private final static String SUB_DEVICE_PSK = "sub_dev_psk";

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


//            mqttLocalSample=new MQTTLocalSample(new SelfMqttActionCallBack(),mBrokerURL,mProductID,mDevName,mDevPSK,mSubProductID,mSubDevName,mTestTopic);
//            mqttLocalSample.connect();

            sleep(2000);}
//        } else {
//            //执行其余操作
//        }
        turnOnHandbrake();
        Log.d(TAG, "onCreate: ");


        /**常亮*/
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();


        imageButton_forward=findViewById(R.id.forward);
        imageButton_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Forward");
                //发送挂挡及前进指令
            }
        });

        imageButton_backward=findViewById(R.id.backward);
        imageButton_backward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: backwarDown");
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.d(TAG, "onTouch: backward");
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "onTouch: backwarding");
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG, "onTouch: Up");
                }
                return true;
            }
        });



        //下拉单选按钮
        Video_Modul_Spinner = (Spinner)findViewById(R.id.Spinner_VIdeo_Model);
        Video_Modul_Spinner.setSelection(0);//进入不会自动播放
        Video_Modul_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    String result = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(RemoteControl.this, result, Toast.LENGTH_SHORT).show();
                    switch (position) {
                        case 0: {
                            /**前摄像*/
                            list = new ArrayList<VideoijkBean>();
                            //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
                            //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
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
                                        Intent intent=new Intent(RemoteControl.this,VideoMonitoring.class);
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
                                                    Intent intent=new Intent(RemoteControl.this,VideoMonitoring.class);
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
                            player = new PlayerView(mActivity, rootView)
                                    .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                                    .setScaleType(PlayStateParams.fillparent) //视频界面剪裁设置
                                    .forbidTouch(false)
                                    .hideSteam(true)
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
                                        Intent intent=new Intent(RemoteControl.this,VideoMonitoring.class);
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
                                                    Intent intent=new Intent(RemoteControl.this,VideoMonitoring.class);
                                                    startActivity(intent);
                                                }
                                            });
                                            AlertDialog b=builder.create();
                                            b.show();
                                        }
                                    })
                                    .setPlaySource(url3)
                                    .startPlay();

                        }
                        break;

                        case 2: {
                            /**左摄像*/
                            //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
                            //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
                            String url4 = "http://ivi.bupt.edu.cn/hls/cctv3.m3u8";
                            player = new PlayerView(mActivity, rootView)
                                    .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                                    .setScaleType(PlayStateParams.fillparent) //视频界面剪裁设置
                                    .forbidTouch(false)
                                    .hideSteam(true)
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
                                        Intent intent=new Intent(RemoteControl.this,VideoMonitoring.class);
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
                                                    Intent intent=new Intent(RemoteControl.this,VideoMonitoring.class);
                                                    startActivity(intent);
                                                }
                                            });
                                            AlertDialog b=builder.create();
                                            b.show();
                                        }
                                    })
                                    .setPlaySource(url4)
                                    .startPlay();
                        }
                        break;

                        case 3: {
                            /**右摄像*/
                            String url5 = "http://ivi.bupt.edu.cn/hls/cctv4.m3u8";
                            player = new PlayerView(mActivity, rootView)
                                    .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                                    .setScaleType(PlayStateParams.fillparent) //视频界面剪裁设置
                                    .forbidTouch(false)
                                    .hideSteam(true)
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
                                        Intent intent=new Intent(RemoteControl.this,VideoMonitoring.class);
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
                                                    Intent intent=new Intent(RemoteControl.this,VideoMonitoring.class);
                                                    startActivity(intent);
                                                }
                                            });
                                            AlertDialog b=builder.create();
                                            b.show();
                                        }
                                    })

                                    .setPlaySource(url5)
                                    .startPlay();

                        }
                        break;

                        case 4: {
                            /**上帝*/

                            String url6 = "http://ivi.bupt.edu.cn/hls/cctv13.m3u8";
                            player = new PlayerView(mActivity, rootView)
                                    .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                                    .setScaleType(PlayStateParams.fillparent) //视频界面剪裁设置
                                    .forbidTouch(false)
                                    .hideSteam(true)
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
                                        Intent intent=new Intent(RemoteControl.this,VideoMonitoring.class);
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
                                                    Intent intent=new Intent(RemoteControl.this,VideoMonitoring.class);
                                                    startActivity(intent);
                                                }
                                            });
                                            AlertDialog b=builder.create();
                                            b.show();
                                        }
                                    })
                                    .setPlaySource(url6)
                                    .startPlay();

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

    private void turnOnHandbrake() {
        Handbrake  mHandbrakeOn = new Handbrake();
        mHandbrakeOn.setTimestamp(System.currentTimeMillis());
        mHandbrakeOn.setStatus(0);
        mHandbrakeOn.setType(14);
        mHandbrakeOn.setTaskid("6D");
        // 需先在腾讯云控制台，增加自定义主题: data，用于更新自定义数据
        mqttSample.publishTopic("data", JSON.toJSONString(mHandbrakeOn));
        Log.d(TAG, "onClick: "+JSON.toJSONString(mHandbrakeOn));
    }
    private void turnOffHandbrake(){
        Handbrake  mHandbrakeOn = new Handbrake();
        mHandbrakeOn.setTimestamp(System.currentTimeMillis());
        mHandbrakeOn.setStatus(0);
        mHandbrakeOn.setType(14);
        mHandbrakeOn.setTaskid("6D");
        // 需先在腾讯云控制台，增加自定义主题: data，用于更新自定义数据
        mqttSample.publishTopic("data", JSON.toJSONString(mHandbrakeOn));
        Log.d(TAG, "onClick: "+JSON.toJSONString(mHandbrakeOn));
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

}
