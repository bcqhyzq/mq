package com.bcqh.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Description:
 *
 * @author yzq
 * @date 2020/6/30
 */
public class RabbitMqUtil {

    private static ConnectionFactory connectionFactory;

    static {
        //1：创建连接mq的工厂对象
        connectionFactory = new ConnectionFactory();
        //2：设置连接主机和端口
        connectionFactory.setHost("60.205.254.33");
        connectionFactory.setPort(5672);
        //3：设置连接虚拟主机，访问虚拟主机的用户和密码
        connectionFactory.setVirtualHost("/bcqh");
        connectionFactory.setUsername("user");
        connectionFactory.setPassword("123456");
    }
    /**
     * 获取连接对象
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭方法
     *
     * @param channel
     * @param connection
     */
    public static void closeChanelAndConnection(Channel channel, Connection connection) {
        try {
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
