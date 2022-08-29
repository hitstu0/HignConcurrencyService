package com.example.loginservice.Mybatis.Mapper;

import com.example.loginservice.Data.User.User;
import com.example.loginservice.Data.User.UserRegisterInfo;

public interface UserMapper {
    void addUser(User user);

    User getUserByMobile(Long mobile);
}
