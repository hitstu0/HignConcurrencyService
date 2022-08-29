package com.example.redisconnect.RedisDataSource;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.example.redisconnect.DiscoverService.DiscoverService;
import com.example.redisconnect.Util.RedisConfig;

import redis.clients.jedis.JedisPool;

public class RedisService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RedisService.class);
    
    private String name;
    private DiscoveryClient discoveryClient;
    private JedisPool jedisPool;
    private RedisConfig config;


    public RedisService(String name, RedisConfig config, DiscoveryClient discoveryClient) {
        this.name = name;
        this.discoveryClient = discoveryClient;
        this.config = config;

        logger.info("begin init redisDataSource, name={}", name);
        DiscoverService.setDiscoveryClient(discoveryClient);
        List<ServiceInstance> instances = DiscoverService.getServiceList(name);
        if (instances == null || instances.size() == 0) {
            logger.error("can not find service name:{}", name);
        } else {
            ServiceInstance instance = instances.get(0);
            logger.info("find instance,name{}, host:{}, port:{} ", name, instance.getHost(), instance.getPort());

            jedisPool = DiscoverService.getJedisPool(config, instance);
            logger.info("get jedisPool success");
        }
    }
    
    public JedisPool GetJedisPool() {
        return jedisPool;
    }
    
}
