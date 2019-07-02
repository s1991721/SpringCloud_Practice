package com.ljf.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by mr.lin on 2019/6/27
 */
@RestController
@DefaultProperties(defaultFallback = "defaultfallback")
public class HystrixController {

    //超时设置
//    @HystrixCommand(fallbackMethod = "fallback",
//            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//设置熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //half open 请求数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //熔断持续的时间10s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //错误率60%，触发熔断
    })
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }

        throw new RuntimeException("error");

//        RestTemplate restTemplate = new RestTemplate();
//
//        return restTemplate.postForObject("http://localhost:8080/product/listForOrder",
//                Arrays.asList("157875196366160022"),
//                String.class);
    }

    private String fallback() {
        return "太拥挤了，请稍后再试";
    }

    private String defaultfallback() {
        return "默认提示:太拥挤了，请稍后再试";
    }

}
