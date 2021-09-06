package com.liangzc.example.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorMain {


    public static void main(String[] args) throws Exception {

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                connectionTimeoutMs(20000)//连接超时时间
                .connectString("49.234.229.49:2181")
                //重试次数
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .sessionTimeoutMs(15000)//会话过期时间
                .build();

        curatorFramework.start();//启动

        //获取节点test下的值
        byte[] bytes = curatorFramework.getData().forPath("/test");
        System.out.println(new String(bytes));
    }
}
