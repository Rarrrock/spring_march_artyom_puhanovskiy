package com.hw.spring_hw_ap.controller;

import com.hw.spring_hw_ap.models.Maintenance;
import com.hw.spring_hw_ap.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.findAll();
    }

    @GetMapping("/{id}")
    public Maintenance getMaintenanceById(@PathVariable String id) {
        return maintenanceService.findById(id);
    }

    @PostMapping
    public Maintenance createMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.save(maintenance);
    }

    @PutMapping("/{id}")
    public Maintenance updateMaintenance(@PathVariable String id, @RequestBody Maintenance maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @DeleteMapping("/{id}")
    public void deleteMaintenance(@PathVariable String id) {
        maintenanceService.deleteById(id);
    }
}