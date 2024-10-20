package com.hw.spring_hw_ap.controller;

import com.hw.spring_hw_ap.config_properties.FuelType;
import com.hw.spring_hw_ap.config_properties.ReferenceDataProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ref-data")
@RequiredArgsConstructor
public class ReferenceDataController {

    @Value("${ref-data.engine-types}")
    private List<String> engineTypes;

    private final ReferenceDataProperties fuelTypeConfig;

    @GetMapping("/engine-types")
    public List<String> getEngineTypes() {
        return engineTypes;
    }

    @GetMapping("/fuel-types")
    public Map<String, List<String>> getFuelTypes() {
        return fuelTypeConfig.getFuelTypes();
    }

    @GetMapping("/fuel-types/{fuelName}")
    public FuelType getFuelTypeByName(@PathVariable String fuelName) {
        return new FuelType(fuelName, fuelTypeConfig.getFuelTypes().get(fuelName));
    }
}
