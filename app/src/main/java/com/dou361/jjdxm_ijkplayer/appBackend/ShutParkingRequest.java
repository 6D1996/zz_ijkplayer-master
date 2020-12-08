package com.dou361.jjdxm_ijkplayer.appBackend;

public class ShutParkingRequest {
    private String requestId,userId,vin;
    private Integer parkingServiceType;

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setParkingServiceType(Integer parkingServiceType) {
        this.parkingServiceType = parkingServiceType;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getUserId() {
        return userId;
    }

    public String getVin() {
        return vin;
    }

    public Integer getParkingServiceType() {
        return parkingServiceType;
    }

    @Override
    public String toString() {
        return "ShutParkingRequest{" +
                "requestId='" + requestId + '\'' +
                ", userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                ", parkingServiceType=" + parkingServiceType +
                '}';
    }
}
