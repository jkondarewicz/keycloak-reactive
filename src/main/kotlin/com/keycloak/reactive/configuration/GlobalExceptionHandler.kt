package com.keycloak.reactive.configuration

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.time.ZoneOffset

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception) =
        ResponseEntity
            .status(500)
            .body(ErrorResponse(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), 500, e.message))

    data class ErrorResponse(
        val timestamp: Long,
        val status: Int,
        val message: String?
    )

}