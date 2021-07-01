package com.liangzc.example.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerKafka {

    public static void main(String[] args) {


        Properties properties = new Properties();
        properties.put("bootstrap.servers", "119.23.189.136:9092");
        properties.put("group.id","gp-test-group");
        //是否自动提交偏移量，只有commit之后才更新消费组的offset
        properties.put("enable.auto.commit", "true");
        //消费者自动提交的间隔
        properties.put("auto.commit.interval.ms","1000");

        //从最早的数据开始消费 earliest|latest | none
        properties.put("auto.offset.reset", "earliest");

        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Arrays.asList("gptest"));

        try {
            while (true){
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : consumerRecords) {
                    System.out.printf("offset=%d,key=%s,value=%s,partition=%s%n", record.offset(), record.key(), record.value(), record.partition());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
