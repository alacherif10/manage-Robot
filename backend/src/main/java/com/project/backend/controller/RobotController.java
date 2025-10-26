package com.project.backend.controller;

import com.project.backend.dto.RobotRequest;
import com.project.backend.dto.RobotResponse;
import com.project.backend.models.Robot;
import com.project.backend.repositories.RobotRepository;
import com.project.backend.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/robot")
public class RobotController {

    @Autowired
    private final RobotService robotService;

    @Autowired
    public RobotController(RobotService robotService){
        this.robotService = robotService;
    }

    @PutMapping("/turn-on")
    public RobotResponse turnOn(@RequestBody RobotRequest request){
        String id = request.getId();
        Robot robot = robotService.turnOn(id);
        return new RobotResponse(robot.getId(),robot.getStatus(), robot.getBatteryLevel(), robot.getWaterLevel(), robot.isOn());
    }

    @PutMapping("/turn-off")
    public RobotResponse turnOff(@RequestBody RobotRequest request){
        String id = request.getId();
        Robot robot = robotService.turnOff(id);
        return new RobotResponse(robot.getId(),robot.getStatus(), robot.getBatteryLevel(), robot.getWaterLevel(), robot.isOn());
    }


}
