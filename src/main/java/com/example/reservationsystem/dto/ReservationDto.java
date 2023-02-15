package com.example.reservationsystem.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {
    @Min(value = 1, message = "Room id must be greater than 0")
    private long roomId;
    @FutureOrPresent
    private LocalDate date;
    private int from;
    private int to;
}
