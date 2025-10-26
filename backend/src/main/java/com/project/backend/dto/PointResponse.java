package com.project.backend.dto;

import org.springframework.data.annotation.Id;

public class PointResponse {
    @Id
    private String id;
    private double x;
    private double y;

    public PointResponse(){}

    public PointResponse(String id, double x, double y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getX(){
        return x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getY(){
        return y;
    }
}
