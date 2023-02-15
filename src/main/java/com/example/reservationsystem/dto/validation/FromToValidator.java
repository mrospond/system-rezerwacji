package com.example.reservationsystem.dto.validation;

import com.example.reservationsystem.dto.ReservationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FromToValidator implements ConstraintValidator<FromLessThanTo, ReservationDto> {
    @Override
    public boolean isValid(ReservationDto reservationDto, ConstraintValidatorContext constraintValidatorContext) {
        return reservationDto.getFrom() < reservationDto.getTo();
    }
}
