package com.ljf.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 分布式锁
 * Created by mr.lin on 2019/6/14
 */
@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @param key
     * @param value 时间
     * @return
     */
    public boolean lock(String key, String value) {

        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        String currentValue = redisTemplate.opsForValue().get(key);

        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);//这里只有一个线程进入，第二个线程进入时值已变

            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }

        return false;
    }

    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
