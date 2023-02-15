package com.example.reservationsystem.service.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class InvalidReservationException extends RuntimeException {
    private List<String> errors;

    public InvalidReservationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
