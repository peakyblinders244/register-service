package com.example.registration.handler;

import com.example.registration.wrapper.ListWrapper;
import com.example.registration.wrapper.ObjectResponseWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 1:58 PM 4/17/2022
 * LeHongQuan
 */

@ControllerAdvice
public class ResponseHandle implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object data, @NonNull MethodParameter methodParameter, @NonNull MediaType mediaType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> aClass, @NonNull ServerHttpRequest serverHttpRequest,
                                  @NonNull ServerHttpResponse serverHttpResponse) {
        if (data instanceof ObjectResponseWrapper) return data;
        if (data instanceof ListWrapper) return data;
        if (data instanceof String && ((String) data).contains("openapi")) return data;
        return ObjectResponseWrapper.builder().statusCode(200).data(data).build();
    }
}