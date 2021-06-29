package com.liangzc.example.mq.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerTest {

    private static final String EXCHANGE_DIRECT = "exchange-direct";
    private static final String EXCHANGE_TOPIC = "exchange-topic";
    private static final String EXCHANGE_FANOUT = "exchange-fanout";
    private static final String QUEUE_NAME_1 = "queue-1";
    private static final String QUEUE_NAME_2 = "queue-2";
    private static final String QUEUE_NAME_3 = "queue-3";
    private static final String QUEUE_NAME_4 = "queue-4";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("119.23.189.136");
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
        Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                System.out.println("Received message :" + msg);
                System.out.println("consumerTag :" + consumerTag);
                System.out.println("deliveryTag :" +envelope.getDeliveryTag());
            }
        };
        channel.basicConsume(QUEUE_NAME_1, true, consumer);
        channel.basicConsume(QUEUE_NAME_2, true, consumer);

    }


}
