package com.fintrust.exception_handler_service.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    String errorCode;
    String description;
    HttpStatus httpStatus;
}
