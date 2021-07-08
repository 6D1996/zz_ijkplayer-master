package com.dou361.jjdxm_ijkplayer.service;

import android.util.Log;

public class NtpTime {
    private static final String TAG = "Ntp Time Thread";
    private String ntpServer = "ntp1.aliyun.com";
    private long ntpTimeDiffer =0;
    private boolean ntpFlag=false;

    public long getNtpTimeDiffer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SntpClient sntpClient = new SntpClient();
                ntpTimeDiffer =sntpClient.getNtpMinusSystemTime(ntpServer,3000);
//                Log.d(TAG, "run:Thread "+sntpClient.getNtpTime());
//                ntpTime=sntpClient.getNtpTime();
            }
        });
        thread.start();
        while (thread.isAlive()&&!ntpFlag){
            if(ntpTimeDiffer !=0){
                Log.d(TAG, "onCreate: 线程结束，ntp时间："+ ntpTimeDiffer);
                ntpFlag=true;}
        }
        return ntpTimeDiffer;
    }
}
