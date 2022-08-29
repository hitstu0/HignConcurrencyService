package com.example.orderservice.RMQ;

import java.util.List;

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

import com.example.rmqconnect.RmqService.ConsumerBuilder;

@Component
public class Consumer {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private DiscoveryClient client;

    @Bean(name = "orderConsumer")
    public DefaultMQPushConsumer getOrderConsumer() {
        logger.info("begin consumer create");
        ConsumerBuilder builder = new ConsumerBuilder(client, "rocketmq");
        DefaultMQPushConsumer consumer = null;
        try {
            consumer = builder.getConsumer("consumer", "test", "test", new Test());
            consumer.start();
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.info("consumer start success");
        return consumer;
    }

}

class Test implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        System.out.println(new String(msgs.get(0).getBody()));
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
    
}
