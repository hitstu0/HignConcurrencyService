package com.example.orderservice.Redis.Key;

import com.example.redisconnect.Util.Prefix.abstractPrefix;

public class UserKey extends abstractPrefix{
    

    protected UserKey(String pre, int expireTime) {
        super(pre, expireTime);
    }
    
    public static UserKey Login = new UserKey("login", 1000*60*60*24);
}
