package com.liangzc.example.mq.rabbitmq.reliable.txselect;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
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
//        toppicTest();
        fanoutTest();
    }


    /**
     * channel.txSelect();开启事物机制
     */
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
            //开启事物确认，生产者发送消息给broker，服务器会有一个响应
            channel.txSelect();
            // String exchange, String routingKey, BasicProperties props, byte[] body
//            channel.basicPublish(EXCHANGE_DIRECT, "", null, msg.getBytes());
            channel.basicPublish(EXCHANGE_FANOUT, "", null, msg.getBytes());
            Integer num = 9 / 0;
            channel.basicPublish(EXCHANGE_FANOUT, "", null, String.valueOf(num).getBytes());
            //提交事物给服务器（broker）
            channel.txCommit();
            System.out.println("消息已发送：" + msg);
        } catch (Exception e) {
            try {
                //否则该消息被回滚
                channel.txRollback();
                System.out.println("消息被回滚了！！！");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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

    /**
     * channel.confirmSelect(); confirm确认机制
     *
     * @throws IOException
     * @throws TimeoutException
     */
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
        channel.confirmSelect();
        // 发送消息
        String msg = "GG,没中签！！！";
        // String exchange, String routingKey, BasicProperties props, byte[] body
        channel.basicPublish(EXCHANGE_DIRECT, "aaa", null, msg.getBytes());
//            channel.basicPublish(EXCHANGE_FANOUT, "", null, msg.getBytes());
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {

            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {

            }
        });
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
