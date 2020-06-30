package com.bcqh.springboot.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
@Component
public class FanOutConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "logs",type = "fanout"))
    })
    public void receiver1(String message){
        System.out.println("message1: " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "logs",type = "fanout"))
    })
    public void receiver2(String message){
        System.out.println("message2: " + message);
    }
}
