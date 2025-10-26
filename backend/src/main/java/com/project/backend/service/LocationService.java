package com.project.backend.service;

import com.project.backend.dto.LocationRequest;
import com.project.backend.dto.LocationResponse;
import com.project.backend.dto.PointResponse;
import com.project.backend.models.Location;
import com.project.backend.models.Point;
import com.project.backend.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {
    @Autowired
    private final LocationRepository locationRepo;

    public LocationService(LocationRepository locationRepo){
        this.locationRepo = locationRepo;
    }

    public LocationResponse addLocation(LocationRequest request){
        Location location = new Location(request.getLatitude(), request.getLongitude());
        Location saved = locationRepo.save(location);
        return new LocationResponse(saved.getId(),saved.getLatitude(), saved.getLongitude());
    }

    public LocationResponse getLocation(String id){
        Optional<Location> location = locationRepo.findById(id);
        if (location.isEmpty()){
            throw new RuntimeException("Location with ID " + id + " not found");
        }
        Location location1 = location.get();
        return new LocationResponse(location1.getId(), location1.getLatitude(), location1.getLongitude());
    }

    public List<LocationResponse> getAllLocation(){
        List<Location> locations = locationRepo.findAll();
        return locations.stream()
                .map(l -> new LocationResponse(l.getId(), l.getLatitude(), l.getLongitude()))
                .collect(Collectors.toList());
    }

    public LocationResponse updateLocation(String id, LocationRequest request){
        Optional<Location> locationOpt = locationRepo.findById(id);
        if (locationOpt.isEmpty()) {
            throw new RuntimeException("Location with ID " + id + " not found");
        }
        Location location = locationOpt.get();
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        Location updatedLocation = locationRepo.save(location);
        return new LocationResponse(updatedLocation.getId(), updatedLocation.getLatitude(), updatedLocation.getLongitude());
    }

    public void deleteLocation(String id){
        if (locationRepo.existsById(id)) {
            locationRepo.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete. Location not found with id: " + id);
        }
    }
}
