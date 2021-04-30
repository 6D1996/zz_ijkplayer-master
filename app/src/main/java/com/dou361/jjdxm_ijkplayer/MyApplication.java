package com.dou361.jjdxm_ijkplayer;

import android.app.Application;
import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dou361.jjdxm_ijkplayer.API.EZPlayerToken;
import com.dou361.jjdxm_ijkplayer.videomonitoring.VideoEZplayer;
import com.videogo.openapi.EZOpenSDK;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.tencent.iot.hub.device.java.core.mqtt.TXAlarmPingSender.TAG;

public class MyApplication extends Application {

    private EZPlayerToken ezPlayerToken;

//    private static final String APP_KEY = "fb74f1db939a476dbd768a4705f5129f";
private static final String APP_KEY = "be31b2172a7f4e94a1541b91a5823aa4";
EZOpenSDK ezOpenSDK;
    @Override
    public void onCreate() {
        super.onCreate();
        /** * sdk日志开关，正式发布需要去掉 */
        EZOpenSDK.showSDKLog(true);
        /** * 设置是否支持P2P取流,详见api */
        EZOpenSDK.enableP2P(false);

        /** * APP_KEY请替换成自己申请的 */
        EZOpenSDK.initLib(this, APP_KEY);

        Thread getToken=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(ContentValues.TAG, "獲取Token: ");
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request tokenRequest= new Request.Builder()
                            .url("https://open.ys7.com/api/lapp/token/get?appKey=be31b2172a7f4e94a1541b91a5823aa4&appSecret=127c6dbbd52a00528751fb85796923ba")
                            .post(RequestBody.create(MediaType.parse("application/json"), ""))
                            .build();//创造HTTP请求
                    //执行发送的指令
                    Response tokenResponse = okHttpClient.newCall(tokenRequest).execute();
                    String tokenResponseString = tokenResponse.body().string();
                    Log.d(ContentValues.TAG, "請求到"+tokenResponseString);
                    ezPlayerToken= JSON.parseObject(tokenResponseString,EZPlayerToken.class);
                    Log.d(TAG, "onCreate: "+ezPlayerToken.getData().getAccessToken());
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("POST失敗", "onClick: "+e.toString());
                }
            }
        });
        getToken.start();
        while (getToken.isAlive()){}
        EZOpenSDK.getInstance().setAccessToken(ezPlayerToken.getData().getAccessToken());
    }
}
