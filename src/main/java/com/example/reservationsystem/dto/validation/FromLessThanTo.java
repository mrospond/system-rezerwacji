package com.example.reservationsystem.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FromToValidator.class)
@Documented
public @interface FromLessThanTo {
    String message() default "Start hour must be before end hour";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
