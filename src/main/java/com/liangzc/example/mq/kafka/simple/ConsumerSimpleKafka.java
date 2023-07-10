package com.liangzc.example.mq.kafka.simple;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerSimpleKafka {

    public static void main(String[] args) {

        Properties properties = new Properties();
        //Broker地址
        properties.put("bootstrap.servers","106.55.227.209:9092");
        //消费者组
        properties.put("group.id", "lzc-test-group");
        /**
         * 是否自动提交偏移量，只有commit之后才更新消费组的 offset  对应consumer-offset的topic中的partition
         * true：自动提交，false：必须手动提交，同步提交consumer.commitSync() 和异步提交consumer.commitUsync()
         */
        properties.put("enable.auto.commit", "true");
        //消费者自动提交的间隔
        properties.put("auto.commit.interval.ms", "1000");
        /**
         *      从最早的数据开始消费 earliest | latest | none
         *      latest:从最新的数据开始消费
         *      none：找不到offset关系，将报错
         */
        properties.put("auto.offset.reset", "earliest");
        //key 和 value的序列化方式
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //订阅topic
//        consumer.subscribe(Arrays.asList("lzc-test-topic"));
        //指定分区（partition）去消费
        consumer.assign(Arrays.asList(new TopicPartition("lzc-test-topic",0)));

        try {
            while (true){
                ConsumerRecords<String,String> records=consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String,String> record:records){
                    record.timestampType();
                    System.out.printf("offset = %d ,key =%s, value= %s, partition= %s,timestamp=%s,%n" ,record.offset(),record.key(),record.value(),record.partition(),record.timestamp());
                }
            }
        }finally {
            consumer.close();
        }
    }
}
