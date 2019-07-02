package com.ljf.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by mr.lin on 2019/6/26
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2)
    public String process(Object message) {
        log.info("StreamReceiver: {}", message);
        return "received.";
    }


    @StreamListener(StreamClient.INPUT2)
    public void process2(Object message) {
        log.info("StreamReceiver2: {}", message);
    }
}
