package com.project.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "robots")
public class Robot {
    @Id
    private String id;
    private RobotStatus status;
    private float batteryLevel;
    private float waterLevel;
    private boolean isOn;

    public Robot(){}

    public Robot(RobotStatus status, float batteryLevel, float waterLevel, boolean isOn){
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
