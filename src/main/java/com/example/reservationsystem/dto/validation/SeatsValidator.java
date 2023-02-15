package com.example.reservationsystem.dto.validation;

import com.example.reservationsystem.dto.RoomFilterDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SeatsValidator implements ConstraintValidator<MinSeatNotBiggerThanMaxSeat, RoomFilterDto> {
    @Override
    public boolean isValid(RoomFilterDto roomFilter, ConstraintValidatorContext constraintValidatorContext) {
        return roomFilter.getMinSeats() <= roomFilter.getMaxSeats();
    }
}
