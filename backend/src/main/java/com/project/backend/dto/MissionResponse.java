package com.project.backend.dto;

import com.project.backend.models.MissionStatus;
import com.project.backend.models.Point;
import com.project.backend.models.Robot;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponse {
    @Id
    private String id;
    private String name;
    private MissionStatus status;
    private List<String> pointsIds;
    private String robotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public MissionResponse(String id, String name, MissionStatus status, List<Point> points, Robot robot, LocalDateTime startTime, LocalDateTime endTime){}

    public MissionResponse(String id, String name, MissionStatus status, List<String> pointsIds, String robotId, LocalDateTime startTime, LocalDateTime endTime){
        this.id = id;
        this.name = name;
        this.status = status;
        this.pointsIds = pointsIds;
        this.robotId = robotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setId(){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setRobot(String robotId) {
        this.robotId = robotId;
    }

    public String getRobotId() {
        return robotId;
    }

    public void setPointsIds(List<String> pointsIds) {
        this.pointsIds = pointsIds;
    }

    public List<String> getPointsIds() {
        return pointsIds;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }

    public void setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
    }

    public LocalDateTime getEndTime(){
        return  endTime;
    }
}
