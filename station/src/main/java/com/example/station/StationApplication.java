package com.example.station;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
	exclude = {DataSourceAutoConfiguration.class}, 
    scanBasePackages = {"com.example"}
) 
public class StationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationApplication.class, args);
	}

}
