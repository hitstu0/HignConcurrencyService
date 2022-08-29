package com.example.orderservice.Data.User;

import lombok.Data;

@Data
public class User {
    private int id;
    private long mobile;
    private String name;
    private String password;
    private String salt;
}
