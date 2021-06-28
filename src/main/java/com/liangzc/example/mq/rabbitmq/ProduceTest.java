package com.liangzc.example.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProduceTest {

    private static final String EXCHANGE_NAME = "exchange-direct";
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Channel channel = null;
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername("admin");
            factory.setPassword("admin");
            factory.setHost("119.23.189.136");
            factory.setPort(5672);
            factory.setVirtualHost("/");
            connection = factory.newConnection();


            //创建一个channel
            channel = connection.createChannel();

            // 发送消息
            String msg = "恭喜中签！！！";

            // String exchange, String routingKey, BasicProperties props, byte[] body
            channel.basicPublish(EXCHANGE_NAME, "nai_xue", null, msg.getBytes());

            System.out.println("消息已发送："+msg);

            channel.close();
            connection.close();
    }
}
