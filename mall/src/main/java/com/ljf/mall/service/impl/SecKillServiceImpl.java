package com.ljf.mall.service.impl;

import com.ljf.mall.exception.SellException;
import com.ljf.mall.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mr.lin on 2019/6/14
 */
@Service
public class SecKillServiceImpl {

    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();

        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    @Autowired
    private RedisLock redisLock;

    public String querySecKillProductInfo(String productId) {
        return "总量：" + products.get(productId) +
                "\n还剩：" + stock.get(productId) +
                "\n下单用户数：" + orders.size();
    }

    public void orderProductMockDiffUser(String productId) {

        long time = System.currentTimeMillis() + 10000;

        if (!redisLock.lock(productId, String.valueOf(time))) {
            throw new SellException(101, "未进入！");
        }

        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {
            orders.put(KeyUtil.genUniqueKey(), productId);
            stockNum--;

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

            stock.put(productId, stockNum);
        }

        redisLock.unlock(productId, String.valueOf(time));

    }

}
