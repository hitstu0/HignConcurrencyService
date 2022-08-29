package com.example.loginservice.Logger;

import org.slf4j.Logger;

public class MyLoggerBuilder {
    
    public static Logger GetLogger(Class<?> clazz) {
        return org.slf4j.LoggerFactory.getLogger(clazz);
    }
}
