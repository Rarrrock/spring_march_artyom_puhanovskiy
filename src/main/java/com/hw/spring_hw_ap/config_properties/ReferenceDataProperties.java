package com.hw.spring_hw_ap.config_properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "ref-data")
public class ReferenceDataProperties {
    private Map<String, List<String>> fuelTypes;

    public Map<String, List<String>> getFuelTypes() {
        return fuelTypes;
    }

    public void setFuelTypes(Map<String, List<String>> fuelTypes) {
        this.fuelTypes = fuelTypes;
    }
}
