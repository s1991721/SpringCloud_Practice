package com.ljf.order.controller;

import com.ljf.order.dto.OrderDTO;
import com.ljf.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by mr.lin on 2019/6/26
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process() {
        String message = "now " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/sendMessage2")
    public void process2() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setOrderId("123");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
