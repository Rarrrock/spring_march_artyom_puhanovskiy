package com.hw.spring_hw_ap.validation_3;

import com.hw.spring_hw_ap.config_properties_1.ReferenceDataProperties;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FuelTypeValidator implements ConstraintValidator<ValidFuelType, String> {

    private final ReferenceDataProperties referenceDataProperties1;

    @Override
    public boolean isValid(String fuelType, ConstraintValidatorContext context) {
        if (fuelType == null || fuelType.isBlank()) {
            return false;
        }

        return referenceDataProperties1.getFuelTypes().containsKey(fuelType);
    }
}

