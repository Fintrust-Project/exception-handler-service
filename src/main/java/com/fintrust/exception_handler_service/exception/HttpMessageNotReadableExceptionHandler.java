package com.fintrust.exception_handler_service.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class HttpMessageNotReadableExceptionHandler implements ExceptionHandler{
    @Override
    @PostConstruct
    public void init() {
        ExceptionHandlerFactory.add(HttpMessageNotReadableException.class, this);
    }

    @Override
    public ResponseEntity<?> handleException(Exception e) {
        String errorDetails = "unacceptable json";
        if(e.getCause() instanceof InvalidFormatException invalidFormatException){
            if(invalidFormatException.getTargetType().isEnum()){
                errorDetails =
                    String.format("Invalid enum values: '%s' for the field '%s' . The value must be one of : '%s'", invalidFormatException.getValue(),
                    invalidFormatException.getPath().get(invalidFormatException.getPath().size()-1).getFieldName(),
                    Arrays.toString(invalidFormatException.getTargetType().getEnumConstants()));
            }
        }
        log.error("Exception occurred while parsing json {}", e.getMessage());
        return new ResponseEntity<>(ErrorMessage.builder().description(errorDetails).httpStatus(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
    }
}
