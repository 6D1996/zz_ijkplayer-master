package com.dou361.jjdxm_ijkplayer.videomonitoring;

public class VideoReply extends PublicRequest{
    private String code,message,videoUrl,timestamp;

    public VideoReply() {
    }
    public VideoReply(String Id){}


    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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


    public void initialVideoReply(){
        String initialString="InitialString";
        this.code=initialString;
        this.message=initialString;
        setUserId("6DAndroid");
        this.videoUrl=initialString;
    }
}
