package com.example.reservationsystem.controllers;

import com.example.reservationsystem.database.exceptions.EntityNotFoundException;
import com.example.reservationsystem.service.exceptions.InvalidReservationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ApplicationErrorController {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleError(EntityNotFoundException exception, Model model) {
        model.addAttribute("errors", List.of(exception.getMessage()));
        return "error";
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleError(BindException exception, Model model) {
        List<String> errors = exception.getAllErrors().stream()
                .map(objectError -> {
                    if (objectError instanceof FieldError error) {
                        return error.getField() + ": " + error.getDefaultMessage();
                    }
                    return objectError.getDefaultMessage();
                })
                .toList();
        model.addAttribute("errors", errors);
        return "error";
    }

    @ExceptionHandler(InvalidReservationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleError(InvalidReservationException exception, Model model) {
        model.addAttribute("errors", exception.getErrors());
        return "error";
    }
}
