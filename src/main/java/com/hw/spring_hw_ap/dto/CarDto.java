package com.hw.spring_hw_ap.dto;

import com.hw.spring_hw_ap.validation.ValidFuelType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarDto {

    private Long id;

    @NotBlank(message = "Model name cannot be blank")
    private String model;

    @Min(value = 1, message = "Engine power must be greater than 0")
    private int enginePower;

    @Min(value = 1, message = "Torque must be greater than 0")
    private int torque;

    @NotBlank(message = "Fuel type cannot be blank")
    @ValidFuelType
    private String fuelType;

    @NotBlank(message = "Owner username cannot be blank")
    private String ownerUsername;

    private LocalDateTime lastMaintenanceTimestamp;
}