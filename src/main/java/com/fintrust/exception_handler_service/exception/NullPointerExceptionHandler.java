package com.fintrust.exception_handler_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NullPointerExceptionHandler implements ExceptionHandler{

    @Override
    public void init() {
        ExceptionHandlerFactory.add(NullPointerException.class, this);
    }

    @Override
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .description(e.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
