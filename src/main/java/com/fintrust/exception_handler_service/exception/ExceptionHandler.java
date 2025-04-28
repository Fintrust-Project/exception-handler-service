package com.fintrust.exception_handler_service.exception;

import org.springframework.http.ResponseEntity;

public interface ExceptionHandler {
    void init();
    ResponseEntity<?> handleException(Exception e);
}
