package com.example.registration.handler;

import com.example.registration.wrapper.ObjectResponseWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;


/**
 * 2:03 PM 4/17/2022
 * LeHongQuan
 */

@RestControllerAdvice
public class ErrorHandle extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {RegisterServiceException.class})
    protected ObjectResponseWrapper handleResponseStatusException(RegisterServiceException e) {
        ObjectResponseWrapper.builder().build();
        return ObjectResponseWrapper.builder().statusCode(0).message(e.getReason()).data(e.getData()).build();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ObjectResponseWrapper handleConstraintViolationException(ConstraintViolationException e) {
        return ObjectResponseWrapper.builder().statusCode(0).message(e.getMessage()).build();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status, @NonNull WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        return ResponseEntity.badRequest().body(ObjectResponseWrapper.builder()
                .statusCode(0)
                .message(fieldErrors.get(0).getDefaultMessage())
                .build());
    }
}