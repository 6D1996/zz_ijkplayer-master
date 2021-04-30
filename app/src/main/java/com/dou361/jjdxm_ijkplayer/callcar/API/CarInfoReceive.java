package com.dou361.jjdxm_ijkplayer.callcar.API;

public class CarInfoReceive {

    private String dataResults;
    private String requestId;
    private String userId;
    private String vin;
    private String code;
    private String message;
    private String timestamp;

    public String getDataResults() {
        return dataResults;
    }

    public void setDataResults(String dataResults) {
        this.dataResults = dataResults;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CarInfoReceive{" +
                "dataResults='" + dataResults + '\'' +
                ", requestId='" + requestId + '\'' +
                ", userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

}
