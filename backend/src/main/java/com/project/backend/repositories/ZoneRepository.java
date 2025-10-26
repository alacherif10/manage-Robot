package com.project.backend.repositories;

import com.project.backend.models.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ZoneRepository extends MongoRepository<Zone, String> {
    Optional<Zone> findByName(String name);

}
