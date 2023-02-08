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
@Constraint(validatedBy = SeatsValidator.class)
@Documented
public @interface MinSeatNotBiggerThanMaxSeat {
    String message() default "Minimum number of seats cannot exceed the maximum number of seats";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
