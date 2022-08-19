package com.example.loginservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.loginservice.Service.RegisterService;

@Controller
@RequestMapping("/hello")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @GetMapping
    @ResponseBody
    public void register() {
        service.register();
    }
}
