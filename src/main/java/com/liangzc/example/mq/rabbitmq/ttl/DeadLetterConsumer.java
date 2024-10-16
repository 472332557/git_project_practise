package com.liangzc.example.mq.rabbitmq.ttl;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 实现订单的延迟关闭：使用死信队列方式
 * 创建一个队列（queue），并且指定一个死信交换机，意味着生产者发送消息，到达指定的时间后，会将消息发往死信交换机。
 * 如果没有指定死信交换机，则消息会被直接清除
 * <p>
 * 此时需要创建一个指定的死信交换机（就和创建普通交换机一样），同时需要创建一个死信队列（和创建普通队列一样）和死信交换机绑定，
 * 则消费者就可以从死信队列中获取消息了
 */
public class DeadLetterConsumer {

    private static final String EXCHANGE_NAME = "ttl-exchange";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("121.37.249.94");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();

        //创建一个信道
        Channel channel = connection.createChannel();

        //指定死信交换机
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "20211019-dead-letter-exchange");

        //声明队列，同时指定死信队列(如果不声明交换机的话，使用默认的交换机：AMQP_default)
        channel.queueDeclare("ttl-queue", false, false, false, arguments);
        channel.exchangeDeclare("ttl-exchange", "direct", false, false, false, null);
        channel.queueBind("ttl-queue", "ttl-exchange", "ttl");

        //声明死信交换机
        channel.exchangeDeclare("20211019-dead-letter-exchange", "fanout", false, false, false, null);
        //声明死信队列
        channel.queueDeclare("20211019-dead-letter-queue", false, false, false, null);
        //绑定死信交换机和队列
        channel.queueBind("20211019-dead-letter-queue", "20211019-dead-letter-exchange", "");

        System.out.println("Waiting for message.........");

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
            }
        };

        channel.basicConsume("20211019-dead-letter-queue", true, consumer);

    }
}
