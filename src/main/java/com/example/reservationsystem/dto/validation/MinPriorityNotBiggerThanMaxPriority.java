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
@Constraint(validatedBy = PriorityValidator.class)
@Documented
public @interface MinPriorityNotBiggerThanMaxPriority {
    String message() default "Minimum priority cannot exceed the maximum priority";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
