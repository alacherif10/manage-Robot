package com.project.backend.dto;

import com.project.backend.models.RobotStatus;
import org.springframework.data.annotation.Id;

import java.util.List;

public class RobotResponse {
    @Id
    private String id;
    private RobotStatus status;
    private float batteryLevel;
    private float waterLevel;
    private boolean isOn;

    public RobotResponse(){}

    public RobotResponse(String id, RobotStatus status, float batteryLevel, float waterLevel, boolean isOn){
        this.id = id;
        this.status = status;
        this.batteryLevel = batteryLevel;
        this.waterLevel = waterLevel;
        this.isOn = isOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RobotStatus getStatus() {
        return status;
    }

    public void setStatus(RobotStatus status) {
        this.status = status;
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
