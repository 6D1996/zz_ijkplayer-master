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

    public String getAutoParkingReplyString() {
        return autoParkingReplyString;
    }

    public void setAutoParkingReplyString(String autoParkingReplyString) {
        this.autoParkingReplyString = autoParkingReplyString;
    }

    public String autoParkingReplyString="Initial";

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

    /**
     * Auto park method string
     *
     * @param parkingType      parking type
     *                         //1泊入/2泊出/3叫车
     * @param parkingOutWay    parking out way
     *                         //0半泊出/1完全泊出
     * @param parkingDirection parking direction
     *                         //1左/2右/3前
     * @return the string
     */
    public String AutoParkMethod( String parkingType, String parkingOutWay,String parkingDirection){
        this.setParkingType(parkingType);
        this.setParkingOutWay(parkingOutWay);
        this.setPathData(pathData);
        final String autoParkingRequestJson= JSON.toJSONString(this);//序列化
        Thread requestThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "postVideoRequest: "+autoParkingRequestJson);
                    OkHttpClient autoParkClient=new OkHttpClient();
                    Log.d(TAG, "postVideoRequest: new OkHttpClient()");
                    Request videoRequest= new Request.Builder()
                            .url(hostURL+"autoParking")
                            .post(RequestBody.create(MediaType.parse("application/json"),autoParkingRequestJson))
                            .build();//创造HTTP请求
                    //执行发送的指令
                    Log.d(TAG, "postVideoRequest: 转换成功");
                    Response autoParkResponse = autoParkClient.newCall(videoRequest).execute();
                    String reply=autoParkResponse.body().string();
                    autoParkingReplyString=reply;
                    Log.d(TAG, "postVideoRequest: 返回结果"+autoParkingReplyString);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("POST失敗", "onClick: "+e.toString());
                }
            }
        });

        requestThread.start();
        while (requestThread.isAlive()){
            if("Initial".equals(autoParkingReplyString)){}
            else
                Log.d(TAG, "AutoParkMethod: "+autoParkingReplyString);
        }
/*        try {
            requestThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        Log.d(TAG, "返回结果: "+autoParkingReplyString);
        return autoParkingReplyString;
    }
    public String AutoParkMethod( String parkingType){
        this.setParkingType(parkingType);
        final String autoParkingRequestJson= JSON.toJSONString(this);//序列化
        Thread requestThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "postVideoRequest: "+autoParkingRequestJson);
                    OkHttpClient autoParkClient=new OkHttpClient();
                    Log.d(TAG, "postVideoRequest: new OkHttpClient()");
                    Request videoRequest= new Request.Builder()
                            .url(hostURL+"autoParking")
                            .post(RequestBody.create(MediaType.parse("application/json"),autoParkingRequestJson))
                            .build();//创造HTTP请求
                    //执行发送的指令
                    Log.d(TAG, "postVideoRequest: 转换成功");
                    Response autoParkResponse = autoParkClient.newCall(videoRequest).execute();
                    String reply=autoParkResponse.body().string();
                    autoParkingReplyString=reply;
                    Log.d(TAG, "postVideoRequest: 返回结果"+autoParkingReplyString);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("POST失敗", "onClick: "+e.toString());
                }
            }
        });

        requestThread.start();
        while (requestThread.isAlive()){
            if("Initial".equals(autoParkingReplyString)){}
            else
            Log.d(TAG, "AutoParkMethod: "+autoParkingReplyString);
        }
/*        try {
            requestThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        Log.d(TAG, "返回结果: "+autoParkingReplyString);
        return autoParkingReplyString;
    }
    public String AutoParkMethod( String parkingType,String pathData){
        this.setParkingType(parkingType);
        this.setPathData(pathData);
        final String autoParkingRequestJson= JSON.toJSONString(this);//序列化
        Thread requestThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "postVideoRequest: "+autoParkingRequestJson);
                    OkHttpClient autoParkClient=new OkHttpClient();
                    Log.d(TAG, "postVideoRequest: new OkHttpClient()");
                    Request videoRequest= new Request.Builder()
                            .url(hostURL+"autoParking")
                            .post(RequestBody.create(MediaType.parse("application/json"),autoParkingRequestJson))
                            .build();//创造HTTP请求
                    //执行发送的指令
                    Log.d(TAG, "postVideoRequest: 转换成功");
                    Response autoParkResponse = autoParkClient.newCall(videoRequest).execute();
                    String reply=autoParkResponse.body().string();
                    autoParkingReplyString=reply;
                    Log.d(TAG, "postVideoRequest: 返回结果"+autoParkingReplyString);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("POST失敗", "onClick: "+e.toString());
                }
            }
        });

        requestThread.start();
        while (requestThread.isAlive()){
            if("Initial".equals(autoParkingReplyString)){}
            else
                Log.d(TAG, "AutoParkMethod: "+autoParkingReplyString);
        }
/*        try {
            requestThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        Log.d(TAG, "返回结果: "+autoParkingReplyString);
        return autoParkingReplyString;
    }
}
