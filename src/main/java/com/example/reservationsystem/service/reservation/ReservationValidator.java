package com.example.reservationsystem.service.reservation;

import com.example.reservationsystem.domain.Reservation;

public interface ReservationValidator {
    ValidationResponse validate(Reservation reservation);
}
