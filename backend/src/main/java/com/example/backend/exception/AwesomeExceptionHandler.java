package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(ThereIsNoSuchUserException.class)
    protected ResponseEntity <AwesomeException> handleThereIsNoSuchUserException() {
        return new ResponseEntity<>(new AwesomeException("There is no such user"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ThereIsNoSuchProjectException.class)
    protected ResponseEntity <AwesomeException> handleThereIsNoSuchProjectException() {
        return new ResponseEntity<>(new AwesomeException("There is no such project"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class AwesomeException {
        private String message;
    }
}
