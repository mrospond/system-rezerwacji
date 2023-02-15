package com.example.reservationsystem.dto.validation;

import com.example.reservationsystem.dto.RoomFilterDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeValidator implements ConstraintValidator<FutureDateTime, RoomFilterDto> {
    @Override
    public boolean isValid(RoomFilterDto dto, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate date = dto.getDate() == null ? LocalDate.now() : dto.getDate();
        LocalDateTime now = LocalDateTime.now();

        if (date.isBefore(now.toLocalDate())) {
            return false;
        }
        else if (date.isEqual(now.toLocalDate())) {
            return dto.getHour() > now.getHour();
        }
        else {
            return true;
        }
    }
}
