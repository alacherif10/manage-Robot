package com.project.backend.service;

import com.project.backend.dto.*;
import com.project.backend.models.Robot;
import com.project.backend.models.Scheduler;
import com.project.backend.models.Zone;
import com.project.backend.repositories.RobotRepository;
import com.project.backend.repositories.SchedulerRepository;
import com.project.backend.repositories.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerService {
    private final RobotRepository robotRepo;
    private final ZoneRepository zoneRepo;
    private final SchedulerRepository schedulerRepo;

    public SchedulerService(RobotRepository robotRepo, ZoneRepository zoneRepo, SchedulerRepository schedulerRepo){
        this.robotRepo = robotRepo;
        this.zoneRepo = zoneRepo;
        this.schedulerRepo = schedulerRepo;
    }

    public RobotResponse addRobot(RobotRequest request){
        Robot robot = new Robot();
        robot.setStatus(request.getStatus());
        robot.setBatteryLevel(request.getBatteryLevel());
        robot.setWaterLevel(request.getWaterLevel());

        Robot savedRobot = robotRepo.save(robot);

        return new RobotResponse( savedRobot.getId(),savedRobot.getStatus(), savedRobot.getBatteryLevel(), savedRobot.getWaterLevel(), savedRobot.isOn());
    }

    public List<RobotResponse> listRobots() {
        List<Robot> robots = robotRepo.findAll();
        return robots.stream().map(robot -> new RobotResponse(
                robot.getId(),
                robot.getStatus(),
                robot.getBatteryLevel(),
                robot.getWaterLevel(),
                robot.isOn()
        )).collect(Collectors.toList());
    }

    public RobotResponse getRobot(String id) {
        Robot robot = robotRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Robot not found with id: " + id));
        return new RobotResponse(
                robot.getId(),
                robot.getStatus(),
                robot.getBatteryLevel(),
                robot.getWaterLevel(),
                robot.isOn()
        );
    }

    public void deleteRobot(String id) {
        if (!robotRepo.existsById(id)) {
            throw new RuntimeException("Robot not found with id: " + id);
        }
        robotRepo.deleteById(id);
    }

    public ZoneResponse addZone(ZoneRequest request){
        Zone zone = new Zone();
        zone.setName(request.getName());
        Zone savedZone = zoneRepo.save(zone);
        return new ZoneResponse(savedZone.getId(), savedZone.getName());
    }

    public List<ZoneResponse> listZones() {
        List<Zone> zones = zoneRepo.findAll();
        return zones.stream().map(zone -> new ZoneResponse(
                zone.getId(),
                zone.getName()

        )).collect(Collectors.toList());
    }

    public ZoneResponse getZone(String id) {
        Zone zone = zoneRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found with id: " + id));
        return new ZoneResponse(
                zone.getId(),
                zone.getName()

        );
    }

    public void deleteZone(String id) {
        if (!zoneRepo.existsById(id)) {
            throw new RuntimeException("Zone not found with id: " + id);
        }
        robotRepo.deleteById(id);
    }

    public SchedulerResponse addScheduler(SchedulerRequest request) {
        String robotId = request.getRobotId();
        String zoneId = request.getZoneId();
        Robot robot = robotRepo.findById(robotId).orElseThrow();
        Zone zone = zoneRepo.findById(zoneId).orElseThrow();
        Scheduler assignment = new Scheduler();
        assignment.setRobot(robot);
        assignment.setZone(zone);
        assignment.setStartTime(request.getStartTime());
        assignment.setEndTime(request.getEndTime());
        Scheduler savedAssignment = schedulerRepo.save(assignment);
        return new SchedulerResponse(savedAssignment.getRobot().getId(), savedAssignment.getZone().getId(), savedAssignment.getStartTime(), savedAssignment.getEndTime());
    }

    public List<SchedulerResponse> listSchedulers() {
        List<Scheduler> schedulers = schedulerRepo.findAll();
        return schedulers.stream()
                .map(s -> new SchedulerResponse(
                        s.getRobot().getId(),
                        s.getZone().getId(),
                        s.getStartTime(),
                        s.getEndTime()
                ))
                .collect(Collectors.toList());
    }

    public SchedulerResponse getScheduler(String id) {
        Scheduler scheduler = schedulerRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Scheduler not found with ID: " + id));
        return new SchedulerResponse(
                scheduler.getRobot().getId(),
                scheduler.getZone().getId(),
                scheduler.getStartTime(),
                scheduler.getEndTime()
        );
    }

    public void deleteScheduler(String id) {
        if (!schedulerRepo.existsById(id)) {
            throw new RuntimeException("Scheduler not found with ID: " + id);
        }
        schedulerRepo.deleteById(id);
    }




}
