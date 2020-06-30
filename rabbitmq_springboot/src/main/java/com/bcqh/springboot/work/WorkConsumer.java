package com.bcqh.springboot.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
@Component
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receiver1(String message){
        System.out.println("message1: " +message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receiver2(String message){
        System.out.println("message2: " +message);
    }
}
