package com.example.orderservice.Service;

import javax.annotation.Resource;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.orderservice.Data.User.User;
import com.example.orderservice.RMQ.Util;
import com.example.orderservice.Redis.JedisBuilder;
import com.example.orderservice.Redis.LUA;
import com.example.orderservice.Redis.Key.GoodKey;
import com.alibaba.fastjson.JSON;
import com.example.orderservice.Data.Order.OrderInfo;
import com.example.orderservice.Result.CodeMsg;

import redis.clients.jedis.Jedis;

@Service
public class OrderService {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private JedisBuilder jedisBuilder;

    @Resource(name = "orderProducer")
    private DefaultMQProducer orderProducer;

    public CodeMsg setOrder(User user, OrderInfo orderInfo) {

        //减库存
        Jedis jedis = jedisBuilder.getJedis();
        int result = (int)(long)jedis.evalsha(LUA.ScripCode, 1, 
        GoodKey.GOODKEY.getPrefix() + orderInfo.getGoodsId());
        if (result == -1) {
            return CodeMsg.GOOD_SHORT;
        }

        logger.info("user {} substore success", user.getName());

        //发消息
        String key = orderInfo.getUserId() + ":" + orderInfo.getGoodsId();
        byte[] body = JSON.toJSONString(orderInfo).getBytes();
        Message orderMessage = new Message(Util.ORDER_TOPIC, Util.ORDER_TAG, key, body);

        logger.info("begin send msg, key {}, body {}", key, JSON.toJSONString(orderInfo));

        try {
            orderProducer.send(orderMessage);
        } catch (Exception e) {
            logger.error("send msg fail, exception is {}", e.getMessage());
            return CodeMsg.SEND_MSG_FAIL;
        } 

        return CodeMsg.SUCCESS;
    }
}
