package com.fintrust.exception_handler_service.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorMessage {
    String errorCode;
    String description;
    HttpStatus httpStatus;
}
