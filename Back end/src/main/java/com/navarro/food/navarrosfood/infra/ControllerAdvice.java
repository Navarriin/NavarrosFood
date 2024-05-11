package com.navarro.food.navarrosfood.infra;

import com.navarro.food.navarrosfood.exception.FoodNotFound;
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
    private final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

    @ExceptionHandler(FoodNotFound.class)
    public ResponseEntity<DefaultMessage> foodNotFoundExceptionHandler(FoodNotFound exception) {
        return ResponseEntity.status(NOT_FOUND).body(new DefaultMessage(NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<DefaultMessage> validationExceptionHandler(ValidationException exception) {
        return ResponseEntity.status(BAD_REQUEST).body(new DefaultMessage(BAD_REQUEST, exception.getMessage()));
    }
}
