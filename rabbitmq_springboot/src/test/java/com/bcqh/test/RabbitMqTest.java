package com.bcqh.test;

import com.bcqh.springboot.RabbitmqSpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testPc() {
        rabbitTemplate.convertAndSend("hello", "hello rabbitmq");
    }

    @Test
    public void testWOrk() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "hello work " + i);
        }
    }

    @Test
    public void testFanOut() {
        rabbitTemplate.convertAndSend("logs1", "", "hello fanout");
    }

    @Test
    public void testRouting() {
        rabbitTemplate.convertAndSend("logs2", "info", "hello direct info");
        rabbitTemplate.convertAndSend("logs2", "error", "hello direct error");
        rabbitTemplate.convertAndSend("logs2", "debug", "hello direct debug");
    }

    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("logs3", "user.save", "hello topic == user.save");
        rabbitTemplate.convertAndSend("logs3", "user.page.save", "hello topic == user.page.save");
        rabbitTemplate.convertAndSend("logs3", "user.page", "hello topic == user.page");
    }
}
