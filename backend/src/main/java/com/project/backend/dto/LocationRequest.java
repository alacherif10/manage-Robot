package com.project.backend.dto;

import org.springframework.data.annotation.Id;

public class LocationRequest {
    @Id
    private String id;
    private double latitude;
    private double longitude;

    public LocationRequest(){}
    public LocationRequest(String id,double latitude, double longitude){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
