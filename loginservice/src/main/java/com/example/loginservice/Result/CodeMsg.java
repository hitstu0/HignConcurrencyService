package com.example.loginservice.Result;

import lombok.Data;

@Data
public class CodeMsg {
    private int code;
    private String msg;

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
 
    //用户错误
    public static CodeMsg USER_EXIST = new CodeMsg(100, "user exist");
    public static CodeMsg USER_NOT_EXIST = new CodeMsg(101, "user not exist");
    public static CodeMsg USER_PASSWORD_ERR = new CodeMsg(103, "user password err");

    //Redis错误
    public static CodeMsg REDIS_WRITE_ERR = new CodeMsg(500, "写redis err");

}
