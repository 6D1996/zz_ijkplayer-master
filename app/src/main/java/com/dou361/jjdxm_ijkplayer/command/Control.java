package com.dou361.jjdxm_ijkplayer.command;

public class Control {
    public long timestamp;
    public Double speed,acceleration,wheel_angle;
    public int type = 11;

    public Control() {
    }

    public Control(Double speed, Double acceleration, Double wheel_angle) {
        this.speed = speed;
        this.acceleration = acceleration;
        this.wheel_angle = wheel_angle;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public Double getWheel_angle() {
        return wheel_angle;
    }

    public void setWheel_angle(Double wheel_angle) {
        this.wheel_angle = wheel_angle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
