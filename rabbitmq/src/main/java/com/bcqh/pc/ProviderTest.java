package com.bcqh.pc;

import com.bcqh.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
public class ProviderTest {

    @Test
    public void testSendMessage() throws IOException, TimeoutException {
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
        //7：发布消息
        //参数1：交换机名称；参数2：队列名称；参数3：传递消息额外设置；参数4：消息的具体内容
        channel.basicPublish("","hello",null,"hello rabbitmq".getBytes());
        //8：关闭连接
        RabbitMqUtil.closeChanelAndConnection(channel,connection);
    }
}
