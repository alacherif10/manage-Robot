package com.project.backend.dto;

import com.project.backend.models.RobotStatus;
import org.springframework.data.annotation.Id;

public class RobotRequest {
    @Id
    private String id;
    private RobotStatus status;
    private float batteryLevel;
    private float waterLevel;
    private boolean isOn;

    public RobotRequest(){}

    public RobotRequest(RobotStatus status, float batteryLevel, float waterLevel, boolean isOn){
        this.status = status;
        this.batteryLevel = batteryLevel;
        this.waterLevel = waterLevel;
        this.isOn = isOn;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setStatus(RobotStatus status){
        this.status = status;
    }

    public RobotStatus getStatus(){
        return status;
    }

    public void setBatteryLevel(float batteryLevel){
        this.batteryLevel = batteryLevel;
    }

    public float getBatteryLevel(){
        return batteryLevel;
    }

    public void setWaterLevel(float waterLevel){
        this.waterLevel = waterLevel;
    }

    public float getWaterLevel(){
        return waterLevel;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

}
