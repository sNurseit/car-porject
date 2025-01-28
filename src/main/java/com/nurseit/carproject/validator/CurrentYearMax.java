package com.nurseit.carproject.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CurrentYearMaxValidator.class)
public @interface CurrentYearMax {
    String message() default "A year cannot be longer than today's year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}