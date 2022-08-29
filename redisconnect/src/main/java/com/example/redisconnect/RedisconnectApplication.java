package com.example.redisconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RedisconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisconnectApplication.class, args);
	}

}
