package com.example.reservationsystem.dao;

public class NonExistingSQLTemplateException extends RuntimeException {
    public NonExistingSQLTemplateException(String message) {
        super(message);
    }
}
