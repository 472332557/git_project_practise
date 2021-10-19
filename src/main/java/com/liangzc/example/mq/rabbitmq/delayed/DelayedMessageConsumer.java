package com.liangzc.example.mq.rabbitmq.delayed;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class DelayedMessageConsumer {

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
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        //声明一个延迟交换机
        channel.exchangeDeclare("delay_echange", "x-delayed-message", false, false, arguments);

        //声明队列
        channel.queueDeclare("delay_queue", false, false, false, null);
        //绑定交换机和队列
        channel.queueBind("delay_queue", "delay_echange", "delay");

        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                System.out.println("收到消息：[" + msg + "]\n接收时间：" +sf.format(new Date()));
            }
        };

        // 开始获取消息
        // String queue, boolean autoAck, Consumer callback
        channel.basicConsume("delay_queue", true, consumer);


    }
}
