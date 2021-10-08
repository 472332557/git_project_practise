package com.liangzc.example.juc.blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过Condition实现一个阻塞队列，这里是自己练习，使用了List作为队列结构，方便
 * 实际上一般使用数组或者链表来构建队列结构，只要满足FIFO（先进先出），都可以作为队列使用
 * 通过Condition的await() 和 signal()实现了一个阻塞队列，并且也是一个生产者、消费者模型
 */
public class BlockingQueueExample {

    //List当做队列的结构
    private List<String> items;

    //当前队列的数量
    private volatile int size;

    //队列的总大小
    private volatile int count;

    private Lock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();

    private final Condition notFull = lock.newCondition();

    public BlockingQueueExample(int count) {
        this.count = count;
        this.items = new ArrayList<>(count);
    }

    public void put(String item){

        lock.lock();
        try {
            //如果此时添加数量超过队列总的大小时，阻塞
            if (size >= count){
                System.out.println("队列满了，需要阻塞一会！！！");
                notFull.await();
            }
            ++size;//增加元素个数
            items.add(item);
            System.out.println("生产者生产了："+item);
            //此时队列不为空了，唤醒消费者线程去消费
            notEmpty.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //队列取数据
    public void take(){

        lock.lock();
        try {

            if(items.isEmpty()){
                System.out.println("队列空了，暂停消费！！！");
                notEmpty.await();
            }
            --size;
            String remove = items.remove(0);
            System.out.println("消费者消费了:"+remove);
            //此时唤醒生产者线程去生产
            notFull.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
