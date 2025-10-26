package com.project.backend.dto;

public class ZoneRequest {
    private String name;

    public ZoneRequest(){}

    public ZoneRequest(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
