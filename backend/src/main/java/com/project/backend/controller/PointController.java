package com.project.backend.controller;

import com.project.backend.dto.PointRequest;
import com.project.backend.dto.PointResponse;
import com.project.backend.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/point")
public class PointController {
    @Autowired
    private final PointService pointService;

    public PointController(PointService pointService){
        this.pointService = pointService;
    }

    @PostMapping("/add-point")
    public PointResponse addPoint(@RequestBody PointRequest request){
        return pointService.addPoint(request);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PointResponse>> getAllPoints() {
        return ResponseEntity.ok(pointService.getAllPoints());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointResponse> getPoint(@PathVariable String id){
        PointResponse point = pointService.getPoint(id);
        return ResponseEntity.ok(point);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PointResponse> updatePoint(@PathVariable String id, @RequestBody PointResponse request){
        PointResponse updated = pointService.updatePoint(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePoint(@PathVariable String id){
        pointService.deletePoint(id);
        return ResponseEntity.noContent().build();
    }
}
