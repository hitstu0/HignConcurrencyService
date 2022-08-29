package com.example.loginservice.Controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.loginservice.Data.User.UserRegisterInfo;
import com.example.loginservice.Logger.MyLoggerBuilder;
import com.example.loginservice.Result.CodeMsg;
import com.example.loginservice.Service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private static final Logger logger = MyLoggerBuilder.GetLogger(RegisterController.class);
    
    @Autowired
    private RegisterService service;

    @PostMapping
    @ResponseBody
    public CodeMsg register(@RequestBody UserRegisterInfo userRegisterInfo) {
        logger.info("register: UserRegisterInfo is {}", userRegisterInfo);
        return service.register(userRegisterInfo);
    }
}
