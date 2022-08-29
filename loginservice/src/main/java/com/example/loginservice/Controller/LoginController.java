package com.example.loginservice.Controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.loginservice.Data.User.UserLoginInfo;
import com.example.loginservice.Logger.MyLoggerBuilder;
import com.example.loginservice.Redis.JedisBuilder;
import com.example.loginservice.Result.CodeMsg;
import com.example.loginservice.Service.LoginService;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = MyLoggerBuilder.GetLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping
    @ResponseBody
    public CodeMsg login(HttpServletResponse response, @RequestBody UserLoginInfo loginInfo) {
        logger.info("login: userlogininfo: {}", loginInfo);
        return loginService.doLogin(response, loginInfo);
    }
}
