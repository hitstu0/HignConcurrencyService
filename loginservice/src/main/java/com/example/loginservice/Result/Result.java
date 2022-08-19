package com.example.loginservice.Result;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(CodeMsg codeMsg) {
        this(codeMsg.getCode(), codeMsg.getMsg());
    }

    private Result(T data) {
        this(0,"成功");
        this.data = data;
    }

    public static <T> Result<T> success(T msg) {
        return new Result<>(msg);
    }

    public static Result<CodeMsg> success(CodeMsg msg) {
         return new Result<>(msg);
    }

    public static Result<CodeMsg> error(CodeMsg msg) {
        return new Result<>(msg);
    }
}
