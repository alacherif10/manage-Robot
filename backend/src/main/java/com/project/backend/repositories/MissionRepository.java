package com.project.backend.repositories;

import com.project.backend.models.Mission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MissionRepository extends MongoRepository<Mission, String> {
    List<Mission> findByRobotId(String robotId);
}
