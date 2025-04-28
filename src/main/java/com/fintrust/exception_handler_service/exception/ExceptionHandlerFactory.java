package com.fintrust.exception_handler_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExceptionHandlerFactory {
    private static final Map<String, ExceptionHandler> exceptionMap = new HashMap<>();
    public static final String DEFAULT_EXCEPTION = "Default_Exception";
    public static void add(Class clazz ,ExceptionHandler e){
        exceptionMap.put(clazz.getName(), e);
    }
    public static void add(String clazz, ExceptionHandler e){
        exceptionMap.put(clazz, e);
    }

    // default exception
   private static final ExceptionHandler defaultException = new ExceptionHandler() {
        @Override
        public void init() {
        }

        @Override
        public ResponseEntity<?> handleException(Exception e) {
            return new ResponseEntity<>(ErrorMessage
                    .builder()
                    .description(e.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    };

    public static ExceptionHandler get(final Class clazz){
        if(exceptionMap.containsKey(clazz.getName())){
            return exceptionMap.get(clazz.getName());
        }
        else if(exceptionMap.containsKey(DEFAULT_EXCEPTION)){
            return exceptionMap.get(DEFAULT_EXCEPTION);
        }
        return defaultException;
    }
}
