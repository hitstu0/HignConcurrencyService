package com.example.loginservice.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import com.example.redisconnect.RedisDataSource.RedisService;
import com.example.redisconnect.Util.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisBuilder implements CommandLineRunner{
    
    @Autowired
    private DiscoveryClient discoveryClient;

    private JedisPool jedisPool;

    @Override
    public void run(String... args) throws Exception {
        RedisConfig config = new RedisConfig();
        config.setMaxActive(1000);
        config.setMaxIdle(10000);
        config.setMaxWait(10000);
        config.setTimeOut(10000);
        RedisService redisService = new RedisService("redis", config, discoveryClient);
        jedisPool = redisService.GetJedisPool();
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }
    
}
