package com.project.backend.controller;

import com.project.backend.dto.*;
import com.project.backend.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/scheduler")
public class schedulerController {

    @Autowired
    private final SchedulerService schedulerService;

    @Autowired
    public schedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping("/add-robot")
    public RobotResponse addRobot(@RequestBody RobotRequest request){
        return schedulerService.addRobot(request);
    }

    @GetMapping("/robots")
    public List<RobotResponse> listRobots() {
        return schedulerService.listRobots();
    }

    @GetMapping("/robots/{id}")
    public RobotResponse getRobot(@PathVariable String id) {
        return schedulerService.getRobot(id);
    }

    @DeleteMapping("/robots/{id}")
    public ResponseEntity<?> deleteRobot(@PathVariable String id) {
        schedulerService.deleteRobot(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add-zone")
    public ZoneResponse addZone(@RequestBody ZoneRequest request){
        return schedulerService.addZone(request);
    }

    @GetMapping("/zones")
    public List<ZoneResponse> listZones() {
        return schedulerService.listZones();
    }

    @GetMapping("/zones/{id}")
    public ZoneResponse getZone(@PathVariable String id) {
        return schedulerService.getZone(id);
    }

    @DeleteMapping("/zones/{id}")
    public ResponseEntity<?> deleteZone(@PathVariable String id) {
        schedulerService.deleteZone(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add-scheduler")
    public SchedulerResponse addScheduler(@RequestBody SchedulerRequest request){
        return schedulerService.addScheduler(request);
    }

    @GetMapping("/times")
    public List<SchedulerResponse> listSchedulers() {
        return schedulerService.listSchedulers();
    }

    @GetMapping("/times/{id}")
    public SchedulerResponse getScheduler(@PathVariable String id) {
        return schedulerService.getScheduler(id);
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<?> deleteScheduler(@PathVariable String id) {
        schedulerService.deleteScheduler(id);
        return ResponseEntity.noContent().build();
    }




}
