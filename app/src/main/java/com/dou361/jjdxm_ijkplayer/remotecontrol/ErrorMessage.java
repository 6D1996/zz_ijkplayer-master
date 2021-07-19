package com.dou361.jjdxm_ijkplayer.remotecontrol;

public class ErrorMessage {
    private String broken_type;
    private long remoteControlDate;
    private String vin;
    private long timestampDown;
    private int type;
    private long timestamp;

    public String getBroken_type() {
        return broken_type;
    }

    public void setBroken_type(String broken_type) {
        this.broken_type = broken_type;
    }

    public long getRemoteControlDate() {
        return remoteControlDate;
    }

    public void setRemoteControlDate(long remoteControlDate) {
        this.remoteControlDate = remoteControlDate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public long getTimestampDown() {
        return timestampDown;
    }

    public void setTimestampDown(long timestampDown) {
        this.timestampDown = timestampDown;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
