package com.example.registration.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 10:50 AM 4/17/2022
 * LeHongQuan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ResponseWrapper <T>{
    protected int statusCode;

    protected String message;

    protected T data;
}