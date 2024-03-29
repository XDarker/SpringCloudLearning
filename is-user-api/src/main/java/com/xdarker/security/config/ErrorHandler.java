package com.xdarker.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Created by XDarker
 * @Description TODO
 * @Date 2020/3/31 23:27
 */
@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handle(Exception ex){

        Map<String, Object> info = new HashMap<>();
        info.put("message", ex.getMessage());
        info.put("time", new Date().getTime());

        return info;
    }
}
