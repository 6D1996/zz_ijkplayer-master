package com.dou361.jjdxm_ijkplayer.autopark;

import com.dou361.jjdxm_ijkplayer.videomonitoring.PublicRequest;

public class AutoParkingRequest extends PublicRequest {

    public String parkingType ;            //1泊入/2泊出/3叫车
    public String parkingOutWay ;           //0半泊出/1完全泊出
    public String parkingDirection ;        //1左/2右/3前
    public String pathData;                 //自动叫车时，起止点数据

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getParkingOutWay() {
        return parkingOutWay;
    }

    public void setParkingOutWay(String parkingOutWay) {
        this.parkingOutWay = parkingOutWay;
    }

    public String getParkingDirection() {
        return parkingDirection;
    }

    public void setParkingDirection(String parkingDirection) {
        this.parkingDirection = parkingDirection;
    }

    public String getPathData() {
        return pathData;
    }

    public void setPathData(String pathData) {
        this.pathData = pathData;
    }
}
