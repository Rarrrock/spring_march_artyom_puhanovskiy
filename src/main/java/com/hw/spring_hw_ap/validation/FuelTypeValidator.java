package com.hw.spring_hw_ap.validation;

import com.hw.spring_hw_ap.config_properties.ReferenceDataProperties;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FuelTypeValidator implements ConstraintValidator<ValidFuelType, String> {

    private final ReferenceDataProperties referenceDataProperties;

    @Override
    public boolean isValid(String fuelType, ConstraintValidatorContext context) {
        if (fuelType == null || fuelType.isBlank()) {
            return false;
        }

        return referenceDataProperties.getFuelTypes().containsKey(fuelType);
    }
}

