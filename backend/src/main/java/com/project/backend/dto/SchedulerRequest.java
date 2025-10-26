package com.project.backend.dto;

import java.time.LocalDateTime;

public class SchedulerRequest {
    private String robotId;
    private String zoneId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public SchedulerRequest(){}

    public SchedulerRequest(String robotId, String zoneId, LocalDateTime startTime, LocalDateTime endTime) {
        this.robotId = robotId;
        this.zoneId = zoneId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
