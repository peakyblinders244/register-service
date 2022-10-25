package com.example.registration.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 2:04 PM 4/17/2022
 * LeHongQuan
 */

public class RegisterServiceException extends ResponseStatusException {
    @Getter
    private Object data;

    public RegisterServiceException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public RegisterServiceException(String message, Object data) {
        super(HttpStatus.NOT_FOUND, message);
        this.data = data;
    }
}