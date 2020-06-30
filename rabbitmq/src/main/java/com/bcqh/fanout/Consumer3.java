package com.bcqh.fanout;

import com.bcqh.utils.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
public class Consumer3 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        // 通道绑定交换机
        channel.exchangeDeclare("register", "fanout");
        // 临时队列
        String queue = channel.queueDeclare().getQueue();
        // 临时队列绑定交换机
        channel.queueBind(queue, "register", "");
        // 消息消费
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-3: " + new String(body));
            }
        });
    }
}
