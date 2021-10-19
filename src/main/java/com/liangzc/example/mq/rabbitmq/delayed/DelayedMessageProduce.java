package com.liangzc.example.mq.rabbitmq.delayed;

import com.rabbitmq.client.*;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 实现订单的延迟关闭：使用插件机制，完成延迟投递
 * 延迟投递，通过插件实现，插件交换机类型：x-delayed-message
 * 使用该插件时，必须要安装延迟投递插件：rabbitmq_delayed_message_exchange-3.8.0.ez，需要和rabbitmq版本相对应
 */
public class DelayedMessageProduce {

    public static void main(String[] args) throws IOException, TimeoutException {

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

        // 延时投递，比如延时1分钟
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, +10);//
        Date delayTime = calendar.getTime();

        // 定时投递，把这个值替换delayTime即可
        // Date exactDealyTime = new Date("2019/01/14,22:30:00");

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String msg = "发送时间：" + sf.format(now) + "，投递时间：" + sf.format(delayTime);

        // 延迟的间隔时间，目标时刻减去当前时刻
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("x-delay", delayTime.getTime() - now.getTime());
        AMQP.BasicProperties.Builder props = new AMQP.BasicProperties().builder().headers(headers);
        channel.basicPublish("delay_echange", "delay", props.build(), msg.getBytes());

        channel.close();
        connection.close();

    }
}
