package com.ljf.order.controller;

import com.ljf.product.client.ProductClient;
import com.ljf.product.common.DecreaseStockInput;
import com.ljf.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mr.lin on 2019/6/20
 */
@RestController
@Slf4j
public class ClientController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
//
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
//        1、restful调用
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);
//        log.info(response);


//        2、负载均衡调用
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url, String.class);

//        3、
//        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);


        return productClient.productMsg();

    }

    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfoOutput> productInfoOutputList = productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("response={}", productInfoOutputList);
        return "ok";
    }

    @GetMapping("/decreaseStock")
    public String decreaseStock() {
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("157875227953464068", 10)));
        return "ok";
    }

}
