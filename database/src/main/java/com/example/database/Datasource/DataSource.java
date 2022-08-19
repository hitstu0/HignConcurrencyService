package com.example.database.Datasource;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import com.example.database.Discover.DiscoverService;
import com.example.database.Exception.ServiceNotFoundException;

public class DataSource {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DataSource.class);
    
    // 连接信息
    private String serviceName;
    private String dataBase;
    private String userName;
    private String password;
    
    private DiscoveryClient client;

    public DataSource(String service, String dataBase, String user, String pass,DiscoveryClient client) {
        this.serviceName = service;
        this.dataBase = dataBase;
        this.userName = user;
        this.password = pass;
        this.client = client;
    }

    public void init() throws ServiceNotFoundException {
        logger.info("start init mybatis connection pool,service: {}", serviceName);
        DiscoverService.setDiscoveryClient(client);
        List<ServiceInstance> services = DiscoverService.getServiceList(serviceName);
        if (services == null || services.size() == 0) {
            logger.error("service: {} not found", serviceName);
            throw new ServiceNotFoundException(serviceName);
        }

        ServiceInstance service = services.get(0);
        String host = service.getHost();
        int port = service.getPort();

        logger.info("service host{},servie port{}", host, port);
        
        Builder.initDataSouce(host, port, dataBase, userName, password);
    }
    
    public static void addMapper(Class<?> clazz) {
        Builder.addMapper(clazz);
    }
    
    public SqlSessionFactory getSqlSessionFactory() {
        return Builder.getSqlSessionFactory();
    }
    
}
