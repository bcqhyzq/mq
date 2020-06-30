package com.bcqh.wq;

import com.bcqh.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
public class ProviderTest {

    @Test
    public void testSend() throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue1",true,false,false,null);
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("","queue1",null,(i+"hello work queue").getBytes());
        }
        RabbitMqUtil.closeChanelAndConnection(channel,connection);
    }
}
