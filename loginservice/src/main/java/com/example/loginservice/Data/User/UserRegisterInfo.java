package com.example.loginservice.Data.User;

import lombok.Data;

@Data
public class UserRegisterInfo {
    private String name;
    private String password;
    private long mobile;
    private String salt;
}
