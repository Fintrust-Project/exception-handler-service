package com.fintrust.exception_handler_service;

import com.fintrust.exception_handler_service.exception.ExceptionHandlerFactory;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<?> handleException(final Exception e){
        Class<?> clazz = e.getClass();
        if(e instanceof FeignException){
            clazz = FeignException.class;
        }
       return ExceptionHandlerFactory.get(clazz).handleException(e);
    }
}
