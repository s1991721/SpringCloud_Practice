package com.ljf.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mr.lin on 2019/6/20
 */
@RestController
public class ServerController {

    @GetMapping("msg")
    public String msg(){
        return "product";
    }

}
