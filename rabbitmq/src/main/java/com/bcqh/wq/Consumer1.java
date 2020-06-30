package com.bcqh.wq;

import com.bcqh.utils.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("queue1", true, false, false, null);
        channel.basicConsume("queue1", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者-1: " + new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
