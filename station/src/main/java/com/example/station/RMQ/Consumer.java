package com.example.station.RMQ;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.example.rmqconnect.RmqService.ConsumerBuilder;
import com.example.station.Data.OrderInfo;
import com.example.station.Mybatis.SqlSessionBuilder;
import com.example.station.Mybatis.Mapper.OrderMapper;

@Component
public class Consumer {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private SqlSessionBuilder builder;

    @Bean(name = "orderConsumer")
    public DefaultMQPushConsumer getOrderConsumer() {
        logger.info("begin consumer create");

        ConsumerBuilder builder = new ConsumerBuilder(client, "rocketmq");
        DefaultMQPushConsumer consumer = null;
        try {
            consumer = builder.getConsumer("consumer", Util.ORDER_TOPIC, Util.ORDER_TAG, new Write());
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        
        logger.info("consumer start success");
        return consumer;
    }

}

class Write implements MessageListenerConcurrently {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Write.class);

    @Autowired
    private SqlSessionBuilder builder;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        logger.info("begin handler msg");

        for (MessageExt messageExt : msgs) {
            byte[] msgByte = messageExt.getBody();
            String msgString = new String(msgByte);
            
            logger.info("msg is {}", msgString);

            OrderInfo orderInfo = JSON.toJavaObject(JSON.parseObject(msgString), OrderInfo.class);
            
            SqlSession sqlSession = null;
            try {
                sqlSession = builder.getSqlSession();
                OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
                mapper.addOrder(orderInfo);
                sqlSession.commit();     
            } catch (Exception e) {
                logger.error("handler msg err, err is {}", e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;

            } finally {
                sqlSession.close();
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
    
}
