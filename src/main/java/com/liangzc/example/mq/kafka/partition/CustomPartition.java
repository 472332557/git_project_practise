package com.liangzc.example.mq.kafka.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomPartition implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String keyStr = (String) key;
        System.out.println("取模以后的值==================："+Integer.valueOf(keyStr) % 3);
        return Integer.valueOf(keyStr) % 3;
    }

    @Override
    public void close() {
        System.out.println("关闭。。。。。。。。。。。。。。。");
    }

    @Override
    public void configure(Map<String, ?> configs) {
        System.out.println("配置信息。。。。。。。。。。。。。。。。。。。。");
    }
}
