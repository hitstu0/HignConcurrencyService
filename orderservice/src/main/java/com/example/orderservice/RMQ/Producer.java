package com.example.orderservice.RMQ;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.rmqconnect.RmqService.ProducerBuilder;

@Component
public class Producer {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Producer.class);

    @Autowired
    private DiscoveryClient client;

    @Bean(name = "orderProducer")
    public DefaultMQProducer getOrderProducer() throws MQClientException {
        logger.info("begin producer create");
        ProducerBuilder builder = new ProducerBuilder(client, "rocketmq");
        DefaultMQProducer producer = builder.getProducer("test", 10000);
        producer.start();

        logger.info("producer start success");
        return producer;
    }
}
