package com.project.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "schedulers")
public class Scheduler {
    @Id
    private String id;
    private Robot robot;
    private Zone zone;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Scheduler(){}

    public Scheduler(Robot robot, Zone zone, LocalDateTime startTime, LocalDateTime endTime) {
        this.robot = robot;
        this.zone = zone;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public void setRobot(Robot robot){
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setZone(Zone zone){
        this.zone = zone;
    }

    public Zone getZone() {
        return zone;
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
