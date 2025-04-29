package com.fintrust.exception_handler_service;

import com.fintrust.exception_handler_service.exception.ExceptionHandlerFactory;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(final Exception e){
        Class<?> clazz = e.getClass();
        if(e instanceof FeignException){
            clazz = FeignException.class;
        }
        log.info("processing exception {} ", clazz);
       return ExceptionHandlerFactory.get(clazz).handleException(e);
    }
}
