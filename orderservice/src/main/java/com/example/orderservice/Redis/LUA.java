package com.example.orderservice.Redis;

public class LUA {
    public static String DECRE_STORE = 
    "local store = redis.call('GET',KEYS[1])\n" +
    "if store == '0' then\n" +
    "return -1 end\n" +
    "redis.call('SET',KEYS[1],store - 1)\n"+
    "return 1\n";

    public static String ScripCode;
}
