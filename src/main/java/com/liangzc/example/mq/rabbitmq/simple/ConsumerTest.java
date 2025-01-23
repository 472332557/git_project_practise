package com.liangzc.example.mq.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerTest {

    private static final String EXCHANGE_DIRECT = "exchange-direct";
    private static final String EXCHANGE_TOPIC = "exchange-topic";
    private static final String EXCHANGE_FANOUT = "fanout-exechange";
    private static final String QUEUE_NAME_1 = "queue-1";
    private static final String QUEUE_NAME_2 = "queue-2";
    private static final String QUEUE_NAME_3 = "queue-3";
    private static final String QUEUE_NAME_4 = "queue-4";
    private static final String QUEUE_NAME_5 = "queue-5";
    private static final String QUEUE_NAME_6 = "queue-6";

    public static void main(String[] args) throws IOException, TimeoutException {

//        directTest();
        topicTest();
    }

    public static void fanoutTest() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("121.37.249.94");
        factory.setPort(5672);
        //指定虚拟机
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        //建立连接
        Connection connection = factory.newConnection();
        //创建消息信道
        Channel channel = connection.createChannel();

        //声明交换机:直连类型
//        channel.exchangeDeclare(EXCHANGE_DIRECT, "direct", true, false, null);
        //声明交换机:广播类型
        channel.exchangeDeclare(EXCHANGE_FANOUT, "fanout", true, false, null);

        //声明队列
        channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);
        System.out.println("waiting for message ..........");

        //绑定交换机和队列
//        channel.queueBind(QUEUE_NAME_1, EXCHANGE_TOPIC, "nai_xue");
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_FANOUT, "");
        channel.queueBind(QUEUE_NAME_2, EXCHANGE_FANOUT, "");

        //创建消费者
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received message :" + msg);
                System.out.println("consumerTag :" + consumerTag);
                System.out.println("deliveryTag :" + envelope.getDeliveryTag());
            }
        };
        channel.basicConsume(QUEUE_NAME_1, true, consumer);
        channel.basicConsume(QUEUE_NAME_2, true, consumer);
    }

    public static void directTest() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("121.37.249.94");
        factory.setPort(5672);
        //指定虚拟机
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        //建立连接
        Connection connection = factory.newConnection();
        //创建消息信道
        Channel channel = connection.createChannel();

        //声明交换机:直连类型
        channel.exchangeDeclare(EXCHANGE_DIRECT, "direct", true, false, null);
        //声明交换机:广播类型
//        channel.exchangeDeclare(EXCHANGE_FANOUT, "fanout", true, false, null);

        //声明队列
        channel.queueDeclare(QUEUE_NAME_3, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME_4, true, false, false, null);
        System.out.println("waiting for message ..........");

        //绑定交换机和队列
//        channel.queueBind(QUEUE_NAME_1, EXCHANGE_TOPIC, "nai_xue");
        channel.queueBind(QUEUE_NAME_3, EXCHANGE_DIRECT, "aaa");
        channel.queueBind(QUEUE_NAME_4, EXCHANGE_DIRECT, "");

        //创建消费者
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received message :" + msg);
                System.out.println("consumerTag :" + consumerTag);
                System.out.println("deliveryTag :" + envelope.getDeliveryTag());
            }
        };
        channel.basicConsume(QUEUE_NAME_3, true, consumer);
        channel.basicConsume(QUEUE_NAME_4, true, consumer);
    }

    public static void topicTest() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("121.37.249.94");
        factory.setPort(5672);
        //指定虚拟机
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        //建立连接
        Connection connection = factory.newConnection();
        //创建消息信道
        Channel channel = connection.createChannel();

        //声明交换机:直连类型
        channel.exchangeDeclare(EXCHANGE_TOPIC, "topic", true, false, null);
        //声明交换机:广播类型
//        channel.exchangeDeclare(EXCHANGE_FANOUT, "fanout", true, false, null);

        //声明队列
        channel.queueDeclare(QUEUE_NAME_5, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME_6, true, false, false, null);
        System.out.println("waiting for message ..........");

        //绑定交换机和队列
//        channel.queueBind(QUEUE_NAME_1, EXCHANGE_TOPIC, "nai_xue");
        channel.queueBind(QUEUE_NAME_5, EXCHANGE_TOPIC, "com.#");
        channel.queueBind(QUEUE_NAME_6, EXCHANGE_TOPIC, "com.*");

        //创建消费者
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received message :" + msg);
                System.out.println("consumerTag :" + consumerTag);
                System.out.println("deliveryTag :" + envelope.getDeliveryTag());
                System.out.println("交换机是：" + envelope.getExchange());
                System.out.println("消费的路由键是：" + envelope.getRoutingKey());
                System.out.println("消费内容类型：" + properties.getContentType());
                System.out.println("=============================================================================");
            }
        };
        channel.basicConsume(QUEUE_NAME_5, true, consumer);
        channel.basicConsume(QUEUE_NAME_6, true, consumer);
    }

}
