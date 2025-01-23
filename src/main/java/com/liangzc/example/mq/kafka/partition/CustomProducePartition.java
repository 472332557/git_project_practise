package com.liangzc.example.mq.kafka.partition;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 使用自定义的实现Partitioner接口，实现自己的选择partition的算法。
 */
public class CustomProducePartition {

    public static void main(String[] args) {

        Properties properties = new Properties();
        //Broker地址
        properties.put("bootstrap.servers", "119.23.189.136:9092");
        //key 和 value的序列化方式
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //produce确认机制  0：发出去就确认 | 1：leader副本落盘就确认  | -1：所有follower副本同步完成才确认
        properties.put("acks", "1");
        // 异常自动重试次数
        properties.put("reties", 3);
        //多少条数据发送一次，默认16k
        properties.put("batch.size", 16384);
        //批量发送的等待时间
        properties.put("linger", 5);
        //客户端缓冲区大小，默认32M，满了也会触发消息发送
        properties.put("buffer.memory", 33554432);
        //获取元数据时生产者的阻塞时间，超时后
        properties.put("max.block.ms", 3000);

        properties.put("partitioner.class", "com.liangzc.example.mq.kafka.partition.CustomPartition");

        //创建Sender线程
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("lzc-test-topic", Integer.toString(i), Integer.toString(i)));
            System.out.println("发送:" + i);
        }

        producer.close();

    }
}
