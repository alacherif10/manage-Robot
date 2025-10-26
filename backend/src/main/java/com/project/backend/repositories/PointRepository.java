package com.project.backend.repositories;

import com.project.backend.models.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PointRepository extends MongoRepository<Point, String> {

}
