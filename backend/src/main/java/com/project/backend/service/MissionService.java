package com.project.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.backend.dto.MissionRequest;
import com.project.backend.dto.MissionResponse;
import com.project.backend.dto.PointRequest;
import com.project.backend.models.Mission;
import com.project.backend.models.Point;
import com.project.backend.models.Robot;
import com.project.backend.repositories.MissionRepository;
import com.project.backend.repositories.PointRepository;
import com.project.backend.repositories.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissionService {

    @Autowired
    private final MissionRepository missionRepo;
    private final RobotRepository robotRepo;
    private final PointRepository pointRepo;
    private final MqttPublisherService mqttPublisher;

    public MissionService(MissionRepository missionRepo, RobotRepository robotRepo, PointRepository pointRepo, MqttPublisherService mqttPublisher){
        this.missionRepo = missionRepo;
        this.robotRepo = robotRepo;
        this.pointRepo = pointRepo;
        this.mqttPublisher = mqttPublisher;
    }

    public MissionResponse addMission(MissionRequest request){
        Optional<Robot> robotOpt = robotRepo.findById(request.getRobotId());
        if (robotOpt.isEmpty()) {
            throw new RuntimeException("Robot with ID " + request.getRobotId() + " not found.");
        }
        Robot robot = robotOpt.get();

        // 2. Fetch points (by ID)
        List<Point> points = pointRepo.findAllById(request.getPointsIds());
        if (points.size() != request.getPointsIds().size()) {
            throw new RuntimeException("Some points not found.");
        }

        Mission mission = new Mission(request.getName(), request.getStatus(), points, robot, request.getStartTime(), request.getEndTime());

        Mission saved = missionRepo.save(mission);

        List<PointRequest> pointsDTO = points.stream()
                .map(p -> new PointRequest(p.getId(), p.getX(), p.getY()))
                .collect(Collectors.toList());

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonPayload = mapper.writeValueAsString(pointsDTO);
            mqttPublisher.connectAndPublish(jsonPayload);
            System.out.println("Mission points sent to robot via MQTT.");

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        List<String> pointIds = points.stream()
                .map(Point::getId)
                .collect(Collectors.toList());

        return new MissionResponse(
                saved.getId(),
                saved.getName(),
                saved.getStatus(),
                pointIds,
                robot.getId(),
                saved.getStartTime(),
                saved.getEndTime()
        );
    }

    public MissionResponse getMission(String id){
        Optional<Mission> mission = missionRepo.findById(id);
        if(mission.isEmpty()){
            throw new RuntimeException("Mission with ID " + id + " not found");
        }

        Mission mission1 = mission.get();
        return new MissionResponse(mission1.getId(), mission1.getName(), mission1.getStatus(), mission1.getPoints(), mission1.getRobot(), mission1.getStartTime(), mission1.getEndTime());
    }

    public List<MissionResponse> getAllMissions(){
        List<Mission> missions = missionRepo.findAll();
        return missions.stream()
                .map(m -> new MissionResponse(m.getId(), m.getName(), m.getStatus(), m.getPoints(), m.getRobot(), m.getStartTime(), m.getEndTime()))
                .collect(Collectors.toList());
    }

    public MissionResponse updateMission(String id, MissionRequest request){
        Optional<Mission> missionOpt = missionRepo.findById(id);
        if(missionOpt.isEmpty()){
            throw new RuntimeException("Mission with ID " + id + " is not found");
        }

        List<Point> points = pointRepo.findAllById(request.getPointsIds());
        if (points.size() != request.getPointsIds().size()) {
            throw new RuntimeException("Some points not found");
        }

        Robot robot = robotRepo.findById(request.getRobotId())
                .orElseThrow(() -> new RuntimeException("Robot with ID " + request.getRobotId() + " not found"));

        Mission mission = missionOpt.get();
        mission.setName(request.getName());
        mission.setStatus(request.getStatus());
        mission.setPoints(points);
        mission.setRobot(robot);
        mission.setStartTime(request.getStartTime());
        mission.setEndTime(request.getEndTime());

        Mission savedMission = missionRepo.save(mission);
        return new MissionResponse(savedMission.getId(), savedMission.getName(), savedMission.getStatus(), savedMission.getPoints(), savedMission.getRobot(), savedMission.getStartTime(), savedMission.getEndTime());
    }

    public void deleteMission(String id){
        if (missionRepo.existsById(id)) {
            missionRepo.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete. Mission not found with id: " + id);
        }
    }

    public List<MissionResponse> getMissionsByRobotId(String robotId){
        List<Mission> missions = missionRepo.findByRobotId(robotId);
        return missions.stream()
                .map(m -> new MissionResponse(m.getId(), m.getName(), m.getStatus(), m.getPoints(), m.getRobot(), m.getStartTime(), m.getEndTime()))
                .collect(Collectors.toList());}
}
