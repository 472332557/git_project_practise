package com.liangzc.example.mq.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProduceTest {

    private static final String EXCHANGE_DIRECT = "exchange-direct";
    private static final String EXCHANGE_TOPIC = "exchange-topic";
    private static final String EXCHANGE_FANOUT = "fanout-exechange";

    public static void main(String[] args) throws Exception {

//        directTest();
        toppicTest();

    }


    public static void fanoutTest() {
        Connection connection = null;
        Channel channel = null;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("121.37.249.94");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        try {
            connection = factory.newConnection();
            //创建一个channel
            channel = connection.createChannel();
            // 发送消息
            String msg = "GG,没中签！！！";
            // String exchange, String routingKey, BasicProperties props, byte[] body
//            channel.basicPublish(EXCHANGE_DIRECT, "", null, msg.getBytes());
            channel.basicPublish(EXCHANGE_FANOUT, "", null, msg.getBytes());
            System.out.println("消息已发送：" + msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    public static void directTest() throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("121.37.249.94");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        connection = factory.newConnection();
        //创建一个channel
        channel = connection.createChannel();
        // 发送消息
        String msg = "GG,没中签！！！";
        // String exchange, String routingKey, BasicProperties props, byte[] body
        channel.basicPublish(EXCHANGE_DIRECT, "aaa", null, msg.getBytes());
//            channel.basicPublish(EXCHANGE_FANOUT, "", null, msg.getBytes());
        System.out.println("消息已发送：" + msg);
        channel.close();
        connection.close();
    }

    public static void toppicTest() throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("121.37.249.94");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        connection = factory.newConnection();
        //创建一个channel
        channel = connection.createChannel();
        // 发送消息
        String msg = "GG,没中签！！！";
        // String exchange, String routingKey, BasicProperties props, byte[] body
        channel.basicPublish(EXCHANGE_TOPIC, "com.aa.bb", null, msg.getBytes());
//            channel.basicPublish(EXCHANGE_FANOUT, "", null, msg.getBytes());
        System.out.println("消息已发送：" + msg);
        channel.close();
        connection.close();

    }
}
