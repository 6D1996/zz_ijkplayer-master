package com.dou361.jjdxm_ijkplayer.appBackend;

public class RequestVehicleCondition {
    private String userId,vin,requestId;

    public RequestVehicleCondition() {
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

    @Override
    public String toString() {
        return "RequestVehicleCondition{" +
                "userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
