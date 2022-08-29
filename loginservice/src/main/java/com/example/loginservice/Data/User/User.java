package com.example.loginservice.Data.User;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String password;
    private long mobile;
    private String salt;
}
