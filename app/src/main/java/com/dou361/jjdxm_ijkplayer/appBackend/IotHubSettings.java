package com.dou361.jjdxm_ijkplayer.appBackend;

public class IotHubSettings {
    private String requestId,userId,vin,code,message;
    private Object iotHubSetting;

    public IotHubSettings() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getIotHubSetting() {
        return iotHubSetting;
    }

    public void setIotHubSetting(Object iotHubSetting) {
        this.iotHubSetting = iotHubSetting;
    }

    @Override
    public String toString() {
        return "IotHubSettings{" +
                "requestId='" + requestId + '\'' +
                ", userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", iotHubSetting=" + iotHubSetting +
                '}';
    }
}
