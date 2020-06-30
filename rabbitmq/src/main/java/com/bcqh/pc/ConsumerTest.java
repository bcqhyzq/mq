package com.bcqh.pc;

import com.bcqh.utils.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
public class ConsumerTest {

    public static void main(String[] args) throws IOException{
        Connection connection = RabbitMqUtil.getConnection();
        //5：获取连接的通道
        Channel channel = connection.createChannel();
        //6：通道绑定消息队列
        //参数1：队列名称，如果没有队列，则创建队列
        //参数2：用来定义队列是否持久化
        //参数3：是否独占队列
        //参数4：是否在消费完成之后自动删除队列
        //参数5：额外附加参数
        channel.queueDeclare("hello",false,false,false,null);
        //7：消费消息
        //参数1：消费队列的名称
        //参数2：开启消息的自动确认机制
        //参数3：消费消息的回调接口
        //参数4：消息的具体内容
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
