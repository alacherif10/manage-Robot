package com.project.backend.controller;

import com.project.backend.dto.LocationRequest;
import com.project.backend.dto.LocationResponse;
import com.project.backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private final LocationService locationservice;

    public LocationController(LocationService locationservice){
        this.locationservice = locationservice;
    }

    @PostMapping
    public LocationResponse addLocation(@RequestBody LocationRequest request){
        return locationservice.addLocation(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> getPoint(@PathVariable String id){
        LocationResponse location = locationservice.getLocation(id);
        return ResponseEntity.ok(location);
    }

    @GetMapping
    public ResponseEntity<List<LocationResponse>> getAllLocations() {
        return ResponseEntity.ok(locationservice.getAllLocation());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LocationResponse> updateLocation(@PathVariable String id, @RequestBody LocationRequest request){
        LocationResponse updated = locationservice.updateLocation(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable String id){
        locationservice.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

}
