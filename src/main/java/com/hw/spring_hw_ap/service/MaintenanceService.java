package com.hw.spring_hw_ap.service;

import com.hw.spring_hw_ap.models.Maintenance;
import com.hw.spring_hw_ap.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    public Maintenance findById(String id) {
        return maintenanceRepository.findById(id).orElseThrow();
    }

    public Maintenance save(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public Maintenance update(String id, Maintenance maintenance) {
        maintenance.setId(id);
        return maintenanceRepository.save(maintenance);
    }

    public void deleteById(String id) {
        maintenanceRepository.deleteById(id);
    }
}
