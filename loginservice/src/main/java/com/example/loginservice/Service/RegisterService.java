package com.example.loginservice.Service;

import java.io.InputStream;
import java.util.List;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.example.database.Datasource.DataSource;
import com.ecwid.consul.v1.session.model.Session;
import com.example.loginservice.Data.User.UserRegisterInfo;
import com.example.loginservice.Mybatis.Mapper.UserMapper;
import com.example.loginservice.Result.CodeMsg;
import com.example.loginservice.Result.Result;

@Service
public class RegisterService {

    @Autowired
    private DiscoveryClient client;
    
    public void register() {
        DataSource dataSource = new DataSource("mysql-3306", "myDatas", "root", "123456yd", client);
        dataSource.init();
        dataSource.addMapper(UserMapper.class);
        SqlSessionFactory factory = dataSource.getSqlSessionFactory();
        SqlSession Session = factory.openSession();
        UserMapper userMapper = Session.getMapper(UserMapper.class);
        UserRegisterInfo info = new UserRegisterInfo();
        info.setMobile(1);
        info.setName("name");
        info.setPassword("password");
        info.setSalt("salt");
        userMapper.addUser(info);
        Session.commit();
    }

}
