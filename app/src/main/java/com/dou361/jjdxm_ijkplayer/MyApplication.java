package com.dou361.jjdxm_ijkplayer;

import android.app.Application;
import android.util.Log;

import com.videogo.openapi.EZOpenSDK;

import static com.tencent.iot.hub.device.java.core.mqtt.TXAlarmPingSender.TAG;

public class MyApplication extends Application {

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


        ezOpenSDK.getInstance().setAccessToken("at.8q82facgdj87vddi64cj7dwqdjj4i1wf-8hf27wibc9-0saik8n-m9umptouf");
        Log.d(TAG, "onCreate: "+EZOpenSDK.getInstance().getEZAccessToken());

    }
}
