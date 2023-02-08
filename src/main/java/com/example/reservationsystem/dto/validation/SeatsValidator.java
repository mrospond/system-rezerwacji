package com.example.reservationsystem.dto.validation;

import com.example.reservationsystem.dto.RoomFilterDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SeatsValidator implements ConstraintValidator<MinSeatNotBiggerThanMaxSeat, Object> {
    @Override
    public boolean isValid(Object roomFilter, ConstraintValidatorContext constraintValidatorContext) {
        RoomFilterDto roomFilterDto = (RoomFilterDto) roomFilter;
        return roomFilterDto.getMinSeats() <= roomFilterDto.getMaxSeats();
    }
}
