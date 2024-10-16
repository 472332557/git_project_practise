package com.liangzc.example.mq.rabbitmq.ttl;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProduceTTL {

    private static final String EXCHANGE_NAME = "ttl-exchange";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("121.37.249.94");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();

        //创建一个channel
        Channel channel = connection.createChannel();
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)//持久化消息
                .contentEncoding("UTF-8")
                .expiration("20000")
                .build();

        String msg = "消息过期消息！！！";
        channel.basicPublish("ttl-exchange", "ttl", properties, msg.getBytes());

        System.out.println("消息已发送。。。。。。");
        channel.close();
        connection.close();
    }

}
