package com.bcqh.springboot.qc;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class PcConsumer {

    @RabbitHandler
    public void receiver(String message){
        System.out.println("message: " + message);
    }
}
