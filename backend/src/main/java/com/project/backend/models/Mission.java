package com.project.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "missions")
public class Mission {
    @Id
    private String id;
    private String name;
    private MissionStatus status;
    List<Point> points;
    private Robot robot;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Mission(){}

    public Mission(String name, MissionStatus status, List<Point> points, Robot robot, LocalDateTime startTime, LocalDateTime endTime){
        this.name = name;
        this.status = status;
        this.points = points;
        this.robot = robot;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
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

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
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
