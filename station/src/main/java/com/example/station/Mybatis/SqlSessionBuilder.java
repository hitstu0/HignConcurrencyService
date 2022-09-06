package com.example.station.Mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import com.example.database.Datasource.DataSource;
import com.example.station.Data.OrderInfo;
import com.example.station.Mybatis.Mapper.OrderMapper;

@Component
public class SqlSessionBuilder implements CommandLineRunner{
    
    @Autowired
    private DiscoveryClient client;
    
    private SqlSessionFactory factory;

    @Override
    public void run(String... args) throws Exception {
        DataSource dataSource = new DataSource("mysql", "myDatas", "root", "123456yd", client);
        dataSource.init();
        
        addAlias();
        addMapper();

        factory = dataSource.getSqlSessionFactory();
    }

    private void addMapper() { 
        DataSource.addMapper(OrderMapper.class);
    }

    private void addAlias() {
       DataSource.addAlias("orderInfo", OrderInfo.class);
    }
    
    public SqlSession getSqlSession() {
        return factory.openSession();
    }
}
