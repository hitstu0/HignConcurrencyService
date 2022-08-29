package com.example.loginservice.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.aspectj.apache.bcel.classfile.Code;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.example.loginservice.Data.User.User;
import com.example.loginservice.Data.User.UserLoginInfo;
import com.example.loginservice.Exception.Base.ViewException;
import com.example.loginservice.Logger.MyLoggerBuilder;
import com.example.loginservice.Mybatis.Mapper.UserMapper;
import com.example.loginservice.Mybatis.SqlSessionFactory.SqlSessionBuilder;
import com.example.loginservice.Redis.JedisBuilder;
import com.example.loginservice.Result.CodeMsg;

import redis.clients.jedis.Jedis;

@Service
public class LoginService {
    private static final Logger logger = MyLoggerBuilder.GetLogger(LoginService.class);

    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    @Autowired
    private JedisBuilder jedisBuilder;

    public CodeMsg doLogin(HttpServletResponse response, UserLoginInfo loginInfo) throws ViewException{
        SqlSession session = sqlSessionBuilder.getSqlSession();
        User user = null;
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            user = userMapper.getUserByMobile(loginInfo.getMobile());
            if (user == null) {
                throw new ViewException(CodeMsg.USER_NOT_EXIST);
            }
        } finally {
            session.close();
        }

        //验证密码
        String dbPass = user.getPassword();
        if (!dbPass.equals(loginInfo.getPassword())) {
            throw new ViewException(CodeMsg.USER_PASSWORD_ERR);
        }

        //加入 token
        String token = loginInfo.getMobile() + ":" + loginInfo.getPassword();
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        
        Jedis jedis = null;
        try {
            jedis = jedisBuilder.getJedis();
            jedis.set(token, JSON.toJSONString(user));
        } catch(Exception e) {
            logger.error("write user into redis err", e);
            throw e;
        } finally{
            jedis.close();
        }

        return CodeMsg.SUCCESS;
    }
}
