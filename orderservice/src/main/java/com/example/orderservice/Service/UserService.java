package com.example.orderservice.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.example.orderservice.Data.User.*;
import com.example.orderservice.Redis.JedisBuilder;

import redis.clients.jedis.Jedis;

@Service
public class UserService {
    
    @Autowired
    private JedisBuilder jedisBuilder;
    public User getUserByToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                break;
            }
        }
        if (token == null) {
            return null;
        }
        Jedis jedis = jedisBuilder.getJedis();
        String userJson = jedis.get(token);
        User user = JSON.toJavaObject(JSON.parseObject(userJson), User.class);
        return user;
    }
}
