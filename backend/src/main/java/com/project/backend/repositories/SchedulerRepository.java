package com.project.backend.repositories;

import com.project.backend.models.Scheduler;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchedulerRepository extends MongoRepository<Scheduler, String> {

}
