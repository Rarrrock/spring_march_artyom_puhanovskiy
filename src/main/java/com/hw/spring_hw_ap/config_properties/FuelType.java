package com.hw.spring_hw_ap.config_properties;

import lombok.Data;

import java.util.List;

@Data
public class FuelType {

    private String name;

    private List<String> variants;

    public FuelType(String name, List<String> variants) {
        this.name = name;
        this.variants = variants;
    }
}

