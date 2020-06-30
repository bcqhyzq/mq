package com.bcqh.topics;

import com.bcqh.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机
        // 参数1：交换机名称
        // 参数2：交换机类型，topic：动态路由
        channel.exchangeDeclare("topics","topic");
        String routeKey = "user1.save";
        channel.basicPublish("topics",routeKey,null,(routeKey+" hello topic").getBytes());
        RabbitMqUtil.closeChanelAndConnection(channel,connection);
    }
}
