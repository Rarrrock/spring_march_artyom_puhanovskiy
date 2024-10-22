package com.hw.spring_hw_ap.repository;

import com.hw.spring_hw_ap.entity.Maintenance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaintenanceRepository extends MongoRepository<Maintenance, String> {

}
