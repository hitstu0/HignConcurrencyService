package com.example.orderservice.Exception.Base;


import com.example.orderservice.Result.CodeMsg;

import lombok.Data;

@Data
public class ViewException extends RuntimeException {
    private int code;
    private String msg;
    private CodeMsg codeMsg;
    public ViewException(CodeMsg msg) {
        this.codeMsg = msg;
        this.code = msg.getCode();
        this.msg = msg.getMsg();
    }
}
