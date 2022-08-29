package com.example.orderservice.Service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.orderservice.Data.User.User;
import com.example.orderservice.Redis.JedisBuilder;
import com.example.orderservice.Redis.LUA;
import com.example.orderservice.Redis.Key.GoodKey;
import com.example.orderservice.Data.Order.OrderInfo;
import com.example.orderservice.Result.CodeMsg;

import redis.clients.jedis.Jedis;

@Service
public class OrderService {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private JedisBuilder jedisBuilder;

    public CodeMsg setOrder(User user, OrderInfo orderInfo) {
        Jedis jedis = jedisBuilder.getJedis();
        int result = (int)(long)jedis.evalsha(LUA.ScripCode, 1, 
        GoodKey.GOODKEY.getPrefix() + orderInfo.getGoodsId());
        if (result == -1) {
            return CodeMsg.GOOD_SHORT;
        }
        return CodeMsg.SUCCESS;
    }
}
