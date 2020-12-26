package com.dou361.jjdxm_ijkplayer.mqtt;

public class Breakdown {
    double timestamp;
    int type;
    String broken_type,vin;

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBroken_type() {
        return broken_type;
    }

    public void setBroken_type(String broken_type) {
        this.broken_type = broken_type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "gearShift{" +
                "timestamp=" + timestamp +
                ", type=" + type +
                ", broken_type='" + broken_type + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
