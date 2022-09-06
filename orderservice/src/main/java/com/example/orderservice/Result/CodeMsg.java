package com.example.orderservice.Result;

import lombok.Data;

@Data
public class CodeMsg {

    private int code;
    private String msg;
    
    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
   
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");

    public static CodeMsg REQUEST_USER_NOT_FOUND = new CodeMsg(100, "request user not found");

    public static CodeMsg GOOD_SHORT = new CodeMsg(200, "good sell out");

    public static CodeMsg SEND_MSG_FAIL = new CodeMsg(300, "send msg fail");
}
