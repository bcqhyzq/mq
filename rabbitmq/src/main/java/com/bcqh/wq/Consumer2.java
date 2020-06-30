package com.bcqh.wq;

import com.bcqh.utils.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        // 一次只消费一个消息
        channel.basicQos(1);
        channel.queueDeclare("queue1", true, false, false, null);
        // 关闭消息的自动确机制
        channel.basicConsume("queue1", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2: " + new String(body));
                // 手动开启消息确认
                // 参数1：确认队列中的哪个消息
                // 参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
