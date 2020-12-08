package com.dou361.jjdxm_ijkplayer.appBackend;

public class AutoParkingRequest {
    private String requestId,userId,vin,parkingType, parkingOutWay,parkingDirection,pathData;

    public AutoParkingRequest() {
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

    @Override
    public String toString() {
        return "AutoParkingRequest{" +
                "requestId='" + requestId + '\'' +
                ", userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                ", parkingType='" + parkingType + '\'' +
                ", parkingOutWay='" + parkingOutWay + '\'' +
                ", parkingDirection='" + parkingDirection + '\'' +
                ", pathData='" + pathData + '\'' +
                '}';
    }
}
