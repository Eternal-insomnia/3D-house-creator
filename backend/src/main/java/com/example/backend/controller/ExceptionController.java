package com.example.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.backend.models.ExceptionResponse;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionController {
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class) // Перехватывает исключения
    private ExceptionResponse notFound(EntityNotFoundException ex) {
        return new ExceptionResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class) // Перехватывает исключения
    private ExceptionResponse error(RuntimeException ex) {
        return new ExceptionResponse(ex.getMessage());
    }
}
