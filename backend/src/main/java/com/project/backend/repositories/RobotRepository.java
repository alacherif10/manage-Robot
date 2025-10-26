package com.project.backend.repositories;

import com.project.backend.models.Robot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RobotRepository extends MongoRepository<Robot, String> {
}
