package com.example.registration.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 10:57 AM 4/17/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListWrapper<T> {
    protected int statusCode;

    protected String message;

    private List<T> data;
}