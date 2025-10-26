package com.project.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "points")
public class Point {
    @Id
    private String id;
    private double x;
    private double y;

    public Point(){}

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String getId(){
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
