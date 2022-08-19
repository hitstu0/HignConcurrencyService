package com.example.database.Exception;

public class ServiceNotFoundException extends RuntimeException {
    
    public ServiceNotFoundException(String name) {
        super("service: " + name + " not found");
    }
}
