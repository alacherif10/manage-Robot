package com.project.backend.controller;
import com.project.backend.dto.MissionRequest;
import com.project.backend.dto.MissionResponse;
import com.project.backend.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mission")
public class MissionController {

    @Autowired
    private final MissionService missionService;

    public MissionController(MissionService missionService){
        this.missionService = missionService;
    }

    @PostMapping("/add-mission")
    public MissionResponse addMission(MissionRequest request){
        return missionService.addMission(request);
    }

    @GetMapping
    public ResponseEntity<List<MissionResponse>> allMission(){
        return ResponseEntity.ok(missionService.getAllMissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissionResponse> getMission(@PathVariable String id){
        MissionResponse mission = missionService.getMission(id);
        return ResponseEntity.ok(mission);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MissionResponse> updateMission(@PathVariable String id, @RequestBody MissionRequest request){
        MissionResponse updated = missionService.updateMission(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable String id){
        missionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{robotId}")
    public ResponseEntity<List<MissionResponse>> getMissionByRobot(@PathVariable String robotId){
        return ResponseEntity.ok(missionService.getMissionsByRobotId(robotId));
    }
}
