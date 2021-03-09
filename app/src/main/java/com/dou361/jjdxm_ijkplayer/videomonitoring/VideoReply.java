package com.dou361.jjdxm_ijkplayer.videomonitoring;

public class VideoReply {
    private String requestId,userId,vin,code,message,videoUrl,timestamp;

    public VideoReply() {
    }

    public VideoReply(String userId) {
        this.userId = userId;
    }


    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
        return "VideoReply{" +
                "requestId='" + requestId + '\'' +
                ", userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
    public void initialVideoReply(){
        String initialString="InitialString";
        this.code=initialString;
        this.message=initialString;
        this.userId="6DAndroid";
        this.videoUrl=initialString;
    }
}
