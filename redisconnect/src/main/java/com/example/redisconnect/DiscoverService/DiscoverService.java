package com.example.redisconnect.DiscoverService;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.example.redisconnect.RedisconnectApplication;
import com.example.redisconnect.Util.RedisConfig;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class DiscoverService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DiscoverService.class);
    
    private static DiscoveryClient discoveryClient;

    public static List<ServiceInstance> getServiceList(String name) {
        logger.info("discoveryClient{}",discoveryClient);
        return discoveryClient.getInstances(name);
    }

    public static void setDiscoveryClient(DiscoveryClient client) {
        discoveryClient = client;
    }

    public static JedisPool getJedisPool(RedisConfig config, ServiceInstance instance) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(config.getMaxIdle());
        jedisPoolConfig.setMaxIdle(config.getMaxIdle());
        jedisPoolConfig.setMaxTotal(config.getMaxActive());
        jedisPoolConfig.setMaxWaitMillis(config.getMaxWait());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, instance.getHost(), instance.getPort());   
        return jedisPool;
    }
}
