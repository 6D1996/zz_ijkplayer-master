package com.dou361.jjdxm_ijkplayer.appBackend;

public class ShutParkingReply {
    private String requestID,userId,vin,code,message;

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestID() {
        return requestID;
    }

    public String getUserId() {
        return userId;
    }

    public String getVin() {
        return vin;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ShutParkingReply{" +
                "requestID='" + requestID + '\'' +
                ", userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
