package com.navarro.food.navarrosfood.controllers;

import com.navarro.food.navarrosfood.exception.message.DefaultMessage;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    private final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<DefaultMessage> validationExceptionHandler(ValidationException exception) {
        return ResponseEntity.status(BAD_REQUEST).body(new DefaultMessage(BAD_REQUEST, exception.getMessage()));
    }
}
