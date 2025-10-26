package com.project.backend.service;

import com.project.backend.models.Robot;
import com.project.backend.models.RobotStatus;
import com.project.backend.repositories.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotService {

    @Autowired
    private RobotRepository robotRepo;

    @Autowired
    private MqttPublisherService mqttPublisherService;


    public Robot turnOn(String id){
        Robot robot = getRobotById(id);
        robot.setOn(true);
        robot.setStatus(RobotStatus.ACTIVE);
        mqttPublisherService.connectAndPublish("TURN_ON:" + robot.getId());
        return robotRepo.save(robot);
    }

    public Robot turnOff(String id){
        Robot robot = getRobotById(id);
        robot.setOn(false);
        robot.setStatus(RobotStatus.OFFLINE);
        mqttPublisherService.connectAndPublish("TURN_ON:" + robot.getId());
        return robotRepo.save(robot);
    }

    public Robot getRobotById(String id){
        return robotRepo.findById(id).orElseThrow(() -> new RuntimeException("robot non trouvé id" + id));
    }


}
