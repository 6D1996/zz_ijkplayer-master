package com.dou361.jjdxm_ijkplayer.autopark;

import android.util.Log;

import com.dou361.jjdxm_ijkplayer.videomonitoring.PublicRequest;

import static com.tencent.iot.hub.device.java.main.scenarized.Door.TAG;

public class AutoParkingReply extends PublicRequest{
    private String timestamp,status,error,message,path,code;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void initial(){
        this.code="Initial";
        Log.d(TAG, "initial: ");
    }

    @Override
    public String toString() {
        return "AutoParkingReply{" +
//                "requestId="+getRequestId()+"userId="+getUserId()+
                "timestamp='" + timestamp + '\'' +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
