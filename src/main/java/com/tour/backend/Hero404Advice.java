package com.tour.backend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Hero404Advice {
    @ResponseBody
    @ExceptionHandler(Hero404.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String hero404Handler(Hero404 ex){
        return ex.getMessage();

    }
}
