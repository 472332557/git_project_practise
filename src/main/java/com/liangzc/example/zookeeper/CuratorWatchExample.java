package com.liangzc.example.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;

public class CuratorWatchExample {

    private static CuratorFramework curatorFramework;
    public CuratorWatchExample() {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectionTimeoutMs(20000)//连接超时时间
                .connectString("49.234.229.49:2181")
                //重试次数
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .sessionTimeoutMs(15000)//会话过期时间
                .build();

        curatorFramework.start();//启动
    }

    /**
     * 普通Watch机制，只会触发监听一次
     * @throws Exception
     */
    public void normalWatcher() throws Exception {
        CuratorWatcher curatorWatcher = new CuratorWatcher() {
            @Override
            public void process(WatchedEvent watchedEvent) throws Exception {
                System.out.println("监听到的事件"+watchedEvent.toString());
            }
        };

        String node = curatorFramework.create().forPath("/watcher", "Watcher String".getBytes());
        System.out.println("节点创建成功："+node);

        byte[] data = curatorFramework.getData().usingWatcher(curatorWatcher).forPath(node);
        System.out.println("监听获取到节点数据："+new String(data));

        curatorFramework.setData().forPath("/watcher", "666".getBytes());
        Thread.sleep(2000);
        curatorFramework.setData().forPath("/watcher", "777".getBytes());
    }


    /**
     * 持续监听
     */
    public void persisWatcher(String node){
        CuratorCache curatorCache = CuratorCache.build(curatorFramework, node, CuratorCache.Options.SINGLE_NODE_CACHE);
        CuratorCacheListener listener = CuratorCacheListener.builder().forAll(new CuratorCacheListener() {
            @Override
            public void event(Type type, ChildData oldData, ChildData data) {
                System.out.println("事件类型："+type+":oldData:"+oldData+":data"+data);
            }
        }).build();
        curatorCache.listenable().addListener(listener);
        curatorCache.start();
    }

    public void nodeOperation(String node) throws Exception {
        curatorFramework.create().forPath(node, "presis".getBytes());
        curatorFramework.setData().forPath(node, "persis666".getBytes());
        curatorFramework.delete().forPath(node);
    }


    public static void main(String[] args) throws Exception {
        CuratorWatchExample watchExample = new CuratorWatchExample();
//        watchExample.normalWatcher();

        String node = "/presis-watch";
        watchExample.persisWatcher(node);
        watchExample.nodeOperation(node);
        System.in.read();

    }
}
