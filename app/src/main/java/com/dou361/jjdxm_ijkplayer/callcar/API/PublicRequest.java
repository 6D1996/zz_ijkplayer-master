package com.dou361.jjdxm_ijkplayer.callcar.API;

public class PublicRequest {
    private String requestId;
    private String userId="6DAndroid";
    private String vin="test_car";

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
}
