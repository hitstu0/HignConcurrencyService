package com.example.loginservice.Exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.loginservice.Exception.Base.ViewException;
import com.example.loginservice.Result.CodeMsg;

@ControllerAdvice
@Component
public class MyExceptionHandler {
    @ExceptionHandler(value = ViewException.class)
    @ResponseBody
    public CodeMsg viewExceptionHandler(ViewException e) {
        return e.getCodeMsg();        
    }
}