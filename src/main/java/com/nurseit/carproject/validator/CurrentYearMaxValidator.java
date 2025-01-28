package com.nurseit.carproject.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class CurrentYearMaxValidator implements ConstraintValidator<CurrentYearMax, Integer> {

    @Override
    public void initialize(CurrentYearMax constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value <= Year.now().getValue();
    }
}