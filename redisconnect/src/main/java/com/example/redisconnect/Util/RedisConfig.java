package com.example.redisconnect.Util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class RedisConfig {
    private int timeOut;
    private int maxActive;
    private int maxWait;
    private int maxIdle;
}
