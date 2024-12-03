package com.phoneCodeService.api.exception;

public class PhoneCodeNotFoundException extends RuntimeException {
    public PhoneCodeNotFoundException(String message) {
        super(message);
    }
}
