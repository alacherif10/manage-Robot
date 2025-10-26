package com.project.backend.repositories;

import com.project.backend.models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository <Location, String>{
}
