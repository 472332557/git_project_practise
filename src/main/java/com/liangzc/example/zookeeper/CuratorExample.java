package com.liangzc.example.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorExample {

    private static CuratorFramework curatorFramework;

    public CuratorExample() {

        curatorFramework = CuratorFrameworkFactory.builder().
                connectionTimeoutMs(20000)//连接超时时间
                .connectString("49.234.229.49:2181")
                //重试次数
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .sessionTimeoutMs(15000)//会话过期时间
                .build();

        curatorFramework.start();//启动
    }


    public void zkOperations() throws Exception {

        String value = "hello zk";

        //creatingParentContainersIfNeeded:可以同时创建父节点、子节点
        String node1 = curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.CONTAINER)
                .forPath("/node/node1",value.getBytes());
        System.out.println("创建了节点："+node1);

        Stat stat = new Stat();
        byte[] statBytes = curatorFramework.getData().storingStatIn(stat).forPath(node1);
        System.out.println("节点状态为："+new String(statBytes));

        //修改值
        stat = curatorFramework.setData().withVersion(stat.getAversion()).forPath(node1, "hello zkServer".getBytes());

        System.out.println("stat是："+stat);
        //修改节点之后的值
        byte[] result = curatorFramework.getData().forPath(node1);
        System.out.println("修改节点之后的值："+new String(result));
        System.out.println("开始删除节点");

        curatorFramework.delete().forPath(node1);

        //删除父节点及其子节点
//        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/node");

        Stat stat1 = curatorFramework.checkExists().forPath(node1);
        if(stat1 == null){
            System.out.println("节点删除成功！");
        }


    }

    public static void main(String[] args) throws Exception {

        CuratorExample curatorExample = new CuratorExample();
        curatorExample.zkOperations();
    }
}
