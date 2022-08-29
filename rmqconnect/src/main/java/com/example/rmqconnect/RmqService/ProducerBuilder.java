package com.example.rmqconnect.RmqService;

import java.util.List;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.example.rmqconnect.Discover.DiscoverService;

public class ProducerBuilder {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ProducerBuilder.class);
    
    private DiscoveryClient client;
    private String serviceName;
    public ProducerBuilder() {
        
    }

    public static DefaultMQProducer getProducer(String group, int timeout) {
        DefaultMQProducer producer = new DefaultMQProducer(group);

        DiscoverService.setDiscoveryClient(client);
        List<ServiceInstance> services = DiscoverService.getServiceList(name)
    }
}
