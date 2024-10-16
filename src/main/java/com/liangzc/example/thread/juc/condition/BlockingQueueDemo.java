package com.liangzc.example.thread.juc.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueDemo {

    private List<String> items;

    //元素个数（表示已经添加的元素个数）
    private volatile int size;

    //数组的容量
    private volatile int count;

    private Lock lock = new ReentrantLock();

    //移除元素
    private final Condition notEmpty = lock.newCondition();

    //添加元素
    private final Condition notFull = lock.newCondition();


    public BlockingQueueDemo(int count) {
        this.count = count;
        items = new ArrayList<>(count);
    }

    public void put(String item) throws InterruptedException {
        lock.lock();
        try {
            if (size >= count) {
                //数组满了，需要去阻塞
                System.out.println("阻塞队列满了，等消费！");
                notFull.await();
            }
            ++size;//增加元素个数
            items.add(item);
            System.out.println("生产者生产消息：" + item);
            //唤醒消费者去消费
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }


    public void remove() throws InterruptedException {
        lock.lock();
        try {
            if (size == 0) {
                System.out.println("阻塞队列空了，先等会！");
                notEmpty.await();
            }
            --size;
            String item = items.remove(0);
            System.out.println("消费者消费消息：" + item);
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        BlockingQueueDemo bq = new BlockingQueueDemo(10);

        Thread thread1 = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                String item = "item-" + i;
                try {
                    bq.put(item);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        Thread.sleep(100);

        Thread thread2 = new Thread(() -> {
            Random random = new Random();
            for (; ; ) {
                try {
                    bq.remove();
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
    }
}
