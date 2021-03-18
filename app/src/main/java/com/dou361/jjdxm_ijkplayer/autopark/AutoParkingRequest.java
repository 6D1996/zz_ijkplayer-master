package com.dou361.jjdxm_ijkplayer.autopark;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dou361.jjdxm_ijkplayer.videomonitoring.PublicRequest;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.tencent.iot.hub.device.java.core.mqtt.TXAlarmPingSender.TAG;

public class AutoParkingRequest extends PublicRequest {

    public String parkingType ;            //1泊入/2泊出/3叫车
    public String parkingOutWay ;           //0半泊出/1完全泊出
    public String parkingDirection ;        //1左/2右/3前
    public String pathData;                 //自动叫车时，起止点数据

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getParkingOutWay() {
        return parkingOutWay;
    }

    public void setParkingOutWay(String parkingOutWay) {
        this.parkingOutWay = parkingOutWay;
    }

    public String getParkingDirection() {
        return parkingDirection;
    }

    public void setParkingDirection(String parkingDirection) {
        this.parkingDirection = parkingDirection;
    }

    public String getPathData() {
        return pathData;
    }

    public void setPathData(String pathData) {
        this.pathData = pathData;
    }

    public void AutoParkMethod(){
        final String autoParkingRequestJson= JSON.toJSONString(this);//序列化
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "postVideoRequest: "+autoParkingRequestJson);
                    OkHttpClient videoClient=new OkHttpClient();
                    Request videoRequest= new Request.Builder()
                            .url(hostURL+"autoParking")
                            .post(RequestBody.create(MediaType.parse("application/json"),autoParkingRequestJson))
                            .build();//创造HTTP请求
                    //执行发送的指令
                    Response autoParkInResponse = videoClient.newCall(videoRequest).execute();
                    String replyString=autoParkInResponse.body().string();
                    Log.d(TAG, "run: 返回结果"+replyString);
                    AutoParkingReply autoParkingReply=new AutoParkingReply();
                    autoParkingReply=JSON.parseObject(replyString,AutoParkingReply.class);
                    Log.d(TAG, "run: 返回类"+autoParkingReply.toString());
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("POST失敗", "onClick: "+e.toString());
                }
            }
        }).start();
    }
}
