package com.dou361.jjdxm_ijkplayer.appBackend;

public class IotHubSettingsRequest {
    private String userId,vin;

    public IotHubSettingsRequest() {
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
        return "IotHubSettingsRequest{" +
                "userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
