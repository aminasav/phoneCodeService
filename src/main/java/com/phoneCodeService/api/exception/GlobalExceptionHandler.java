package com.phoneCodeService.api.exception;

import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String EUROPE_MOSCOW = "Europe/Moscow";

    @ExceptionHandler(PhoneCodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage handlePhoneCodeNotFoundException(PhoneCodeNotFoundException e) {
        return new ExceptionMessage(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                ZonedDateTime.now().withZoneSameInstant(ZoneId.of(EUROPE_MOSCOW)));
    }
}
