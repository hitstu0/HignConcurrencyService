package com.example.loginservice.Service;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginservice.Data.User.User;
import com.example.loginservice.Data.User.UserRegisterInfo;
import com.example.loginservice.Exception.Base.ViewException;
import com.example.loginservice.Mybatis.Mapper.UserMapper;
import com.example.loginservice.Mybatis.SqlSessionFactory.SqlSessionBuilder;
import com.example.loginservice.Result.CodeMsg;

@Service
public class RegisterService {
    
    @Autowired
    private SqlSessionBuilder sessionBuilder;

    public CodeMsg register(UserRegisterInfo registerUser) throws ViewException{
        SqlSession session = sessionBuilder.getSqlSession();
        try {          
            User user = null;
            UserMapper userMapper = session.getMapper(UserMapper.class);
            user = userMapper.getUserByMobile(registerUser.getMobile());
            if (user != null) {
                throw new ViewException(CodeMsg.USER_EXIST);
            }

            user = new User();
            user.setMobile(registerUser.getMobile());
            user.setName(registerUser.getName());
            user.setPassword(registerUser.getPassword());
            user.setSalt("salt");
            
            userMapper.addUser(user);
            session.commit();
        } finally {
            session.close();
        }

        return CodeMsg.SUCCESS;
    }

}
