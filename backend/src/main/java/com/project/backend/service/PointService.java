package com.project.backend.service;

import com.project.backend.dto.PointRequest;
import com.project.backend.dto.PointResponse;
import com.project.backend.models.Point;
import com.project.backend.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepo;

    public PointService(PointRepository pointRepo){
        this.pointRepo = pointRepo;
    }

    public PointResponse addPoint(PointRequest request){
        Point point = new Point();
        point.setX(request.getX());
        point.setY(request.getY());
        Point savedPoint = pointRepo.save(point);
        return new PointResponse(savedPoint.getId(), savedPoint.getX(), savedPoint.getY());
    }

    public PointResponse getPoint(String id) {
        Optional<Point> point = pointRepo.findById(id);
        if (point.isEmpty()) {
            throw new RuntimeException("Point with ID " + id + " not found");
        }
        Point point1 = point.get();
        return new PointResponse(point1.getId(), point1.getX(), point1.getY());
    }

    public List<PointResponse> getAllPoints() {
        List<Point> points = pointRepo.findAll();
        return points.stream()
                .map(p -> new PointResponse(p.getId(), p.getX(), p.getY()))
                .collect(Collectors.toList());
    }

    public PointResponse updatePoint(String id, PointResponse request) {
        Optional<Point> pointOpt = pointRepo.findById(id);
        if (pointOpt.isEmpty()) {
            throw new RuntimeException("Point with ID " + id + " not found");
        }
        Point point = pointOpt.get();
        point.setX(request.getX());
        point.setY(request.getY());
        Point updatedPoint = pointRepo.save(point);
        return new PointResponse(updatedPoint.getId(), updatedPoint.getX(), updatedPoint.getY());
    }

    public void deletePoint(String id) {
        if (pointRepo.existsById(id)) {
            pointRepo.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete. Point not found with id: " + id);
        }
    }


}
