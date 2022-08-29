package com.example.database.Datasource;


import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.slf4j.Logger;

public class Builder {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Builder.class);
    
    //连接信息
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String urlPre = "jdbc:mysql://";
    private static final String urlPost = "?useUnicode=true&characterEncoding=utf8&useSSL=false" + 
    "&allowPublicKeyRetrieval=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";

    private static DataSource dataSource;
    private static Configuration configuration;

    static void initDataSouce(String host, int port, String dataBase, String name, String password) {
        //创建连接池
        String url = urlPre + host + ":" + port + "/" + dataBase + urlPost;

        logger.info("datasource url is {}", url);

        dataSource = new PooledDataSource(driver, url, name, password);
        //事物
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //环境
        Environment environment = new Environment("development", transactionFactory, dataSource);
        //配置
        configuration = new Configuration(environment);
        configuration.setMapUnderscoreToCamelCase(true);
    }
    
    static void addMapper(Class<?> clazz) {
        configuration.addMapper(clazz);
    }
    
    static void addAlias(String alias, Class<?> clazz) {
        TypeAliasRegistry registry = configuration.getTypeAliasRegistry();
        registry.registerAlias(alias, clazz);
    }

    static SqlSessionFactory getSqlSessionFactory() {
        return new SqlSessionFactoryBuilder().build(configuration);
    }
   
    

}
