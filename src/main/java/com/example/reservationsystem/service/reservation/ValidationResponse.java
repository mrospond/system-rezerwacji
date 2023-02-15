package com.example.reservationsystem.service.reservation;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResponse {
    private boolean valid;
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }
}
