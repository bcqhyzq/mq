package com.bcqh.springboot.routing;

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
public class RoutingConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "logs2",type = "direct"),key = {
                    "info", "warning", "debug"
            })
    })
    public void receiver1(String message){
        System.out.println("message1: " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "logs2",type = "direct"),key = {
                    "error"
            })
    })
    public void receiver2(String message){
        System.out.println("message2: " + message);
    }
}
