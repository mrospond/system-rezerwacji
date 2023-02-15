package com.example.reservationsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Permissions {
    private long id;
    private double maxReservationTimeHours;
    private int maxRoomSize;
    private int maxReservationsPerDay;
}
