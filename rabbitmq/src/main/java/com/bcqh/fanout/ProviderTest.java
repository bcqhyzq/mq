package com.bcqh.fanout;

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
        Channel channel = connection.createChannel();
        // 声明交换机
        // 参数1：交换机名称
        // 参数2：交换机类型，fanout：广播模式
        channel.exchangeDeclare("register", "fanout");
        channel.basicPublish("register", "", null, "hello fanout".getBytes());
        RabbitMqUtil.closeChanelAndConnection(channel, connection);
    }
}
