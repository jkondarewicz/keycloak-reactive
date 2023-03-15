package com.keycloak.reactive.configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity
                .status(500)
                .body(
                        new ErrorResponse(
                                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                                500,
                                e.getMessage()
                        )
                );
    }

    record ErrorResponse(
        Long timestamp,
        int status,
        String message
    ) {}

}