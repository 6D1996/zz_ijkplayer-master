package com.dou361.jjdxm_ijkplayer.videomonitoring;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dou361.jjdxm_ijkplayer.command.Video;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class VideoOperation {
    public int videoNum,serviceType;
    private VideoRequest videoRequest;
    private String videoResponseString,mergeVideoString;
    public String hostURL="http://vehicleroadcloud.faw.cn:60443/backend/appBackend/";
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
                    if(videoNum!=6){
                        videoResponseString=videoResponse.body().string();}
                    else mergeVideoString=videoResponse.body().string();

                    Log.d(TAG, "run: 返回结果"+videoNum+"路视频：\n"+videoResponseString+"\n"+mergeVideoString);

                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("POST失敗", "onClick: "+e.toString());
                }
            }
        }).start();
        Log.d(TAG, "run: 返回结果"+videoNum+"路视频：\n"+videoResponseString);
        if (videoNum!=6){
            return videoResponseString;}
        else return mergeVideoString;
    }

}
