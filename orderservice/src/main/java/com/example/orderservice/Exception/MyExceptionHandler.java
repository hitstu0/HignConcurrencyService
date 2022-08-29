package com.example.orderservice.Exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.orderservice.Exception.Base.ViewException;
import com.example.orderservice.Result.CodeMsg;



@ControllerAdvice
@Component
public class MyExceptionHandler {
    @ExceptionHandler(value = ViewException.class)
    @ResponseBody
    public CodeMsg viewExceptionHandler(ViewException e) {
        return e.getCodeMsg();        
    }
}