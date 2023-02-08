package com.example.reservationsystem.dto.validation;

import com.example.reservationsystem.dto.RoomFilterDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityValidator implements ConstraintValidator<MinPriorityNotBiggerThanMaxPriority, Object> {
    @Override
    public boolean isValid(Object roomFilter, ConstraintValidatorContext constraintValidatorContext) {
        RoomFilterDto roomFilterDto = (RoomFilterDto) roomFilter;
        return roomFilterDto.getMinPriority() <= roomFilterDto.getMaxPriority();
    }
}
