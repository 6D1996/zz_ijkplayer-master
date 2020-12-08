package com.dou361.jjdxm_ijkplayer.appBackend;

public class CallCarRequest {
    private String requestId,userId,vin,parkingType,pathData;

    public CallCarRequest() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getPathData() {
        return pathData;
    }

    public void setPathData(String pathData) {
        this.pathData = pathData;
    }

    @Override
    public String toString() {
        return "CallCarRequest{" +
                "requestId='" + requestId + '\'' +
                ", userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                ", parkingType='" + parkingType + '\'' +
                ", pathData='" + pathData + '\'' +
                '}';
    }
}
