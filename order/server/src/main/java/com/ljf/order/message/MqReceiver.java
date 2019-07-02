package com.ljf.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by mr.lin on 2019/6/26
 */
@Slf4j
@Component
public class MqReceiver {

//    1、@RabbitListener(queues = "myQueue")

    //    2、自动创建队列
//    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
//    3、
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("MqReceiver:{}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            key = "computer",
            exchange = @Exchange("myOrder"),
            value = @Queue("computerQueue")
    ))
    public void process2(String message) {
        log.info("computer MqReceiver:{}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            key = "fruit",
            exchange = @Exchange("myOrder"),
            value = @Queue("fruitQueue")
    ))
    public void process3(String message) {
        log.info("fruit MqReceiver:{}", message);
    }

}
