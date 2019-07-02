package com.ljf.mall.controller;

import com.ljf.mall.service.impl.SecKillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 压测
 * Created by mr.lin on 2019/6/14
 */
@RestController
@RequestMapping("/skill")
public class SecKillController {

    @Autowired
    private SecKillServiceImpl secKillService;

    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) {

        secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }

}
