package com.example.orderservice.Redis.Key;

import com.example.redisconnect.Util.Prefix.abstractPrefix;

public class GoodKey extends abstractPrefix{
    

    protected GoodKey(String pre, int expireTime) {
        super(pre, expireTime);
    }
    
    public static GoodKey GOODKEY = new GoodKey("good", 1000*60*60*24);
    
}
