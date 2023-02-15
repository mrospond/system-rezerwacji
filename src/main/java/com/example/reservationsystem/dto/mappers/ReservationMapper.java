package com.example.reservationsystem.dto.mappers;

import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.dto.ReservationDto;

public interface ReservationMapper {
    Reservation mapToReservation(ReservationDto dto);
}
