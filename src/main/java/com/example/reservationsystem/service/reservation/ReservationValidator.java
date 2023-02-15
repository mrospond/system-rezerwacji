package com.example.reservationsystem.service.reservation;

import com.example.reservationsystem.domain.Reservation;

public interface ReservationValidator {
    boolean validate(Reservation reservation);
}
