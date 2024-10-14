package com.hw.spring_hw_ap.validation_3;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FuelTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFuelType {

    String message() default "Invalid fuel type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}