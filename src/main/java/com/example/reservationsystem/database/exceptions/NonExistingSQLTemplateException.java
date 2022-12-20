package com.example.reservationsystem.database.exceptions;

public class NonExistingSQLTemplateException extends RuntimeException {
    public NonExistingSQLTemplateException(String message) {
        super(message);
    }
}
