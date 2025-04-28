package com.fintrust.exception_handler_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class IllegalArgumentExceptionHandler implements ExceptionHandler{
    @Override
    public void init() {
        ExceptionHandlerFactory.add(IllegalArgumentException.class, this);
    }

    @Override
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(ErrorMessage.builder().description(e.getMessage()).httpStatus(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
    }
}
