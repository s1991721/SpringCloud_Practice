package com.ljf.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by mr.lin on 2019/6/26
 */
public interface StreamClient {

    String INPUT = "myMessage";
    String INPUT2 = "myMessage2";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(INPUT2)
    MessageChannel output();

}
