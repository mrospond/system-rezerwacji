package com.example.reservationsystem.dto;

import com.example.reservationsystem.dto.validation.MinPriorityNotBiggerThanMaxPriority;
import com.example.reservationsystem.dto.validation.MinSeatNotBiggerThanMaxSeat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@MinSeatNotBiggerThanMaxSeat
@MinPriorityNotBiggerThanMaxPriority
public class RoomFilterDto {
    private String roomName;
    private String building;
    private String city;
    private boolean floor1;
    private boolean floor2;
    private boolean floor3;
    private boolean floor4;
    private List<Boolean> floors;
    @Min(value = 2, message = "Number of seats cannot be less than 2")
    @Max(value = 32, message = "Number of seats cannot exceed 32")
    private int minSeats;
    @Min(value = 2, message = "Number of seats cannot be less than 2")
    @Max(value = 32, message = "Number of seats cannot exceed 32")
    private int maxSeats;
    @Min(value = 1, message = "Priority cannot be less than 1")
    @Max(value = 5, message = "Priority cannot exceed 5")
    private int minPriority;
    @Min(value = 1, message = "Priority cannot be less than 1")
    @Max(value = 5, message = "Priority cannot exceed 5")
    private int maxPriority;
    @NotBlank(message = "Status cannot be blank")
    private String status;
    private String videoConferenceHolder;
}
