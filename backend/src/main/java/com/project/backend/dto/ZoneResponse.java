package com.project.backend.dto;

import org.springframework.data.annotation.Id;

import java.util.List;

public class ZoneResponse {
    @Id
    private String id;
    private String name;
    private List<SchedulerResponse> schedulers;


    public ZoneResponse(String id, String name){
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
