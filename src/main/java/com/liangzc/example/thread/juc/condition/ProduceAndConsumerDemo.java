package com.liangzc.example.thread.juc.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceAndConsumerDemo {

    private List<String> items;

    private volatile int count;

    private volatile int size;

    private Lock lock = new ReentrantLock();

    //生产等待队列
    private final Condition notFull = lock.newCondition();

    //消费者等待队列
    private final Condition notEmpty = lock.newCondition();

    private Random random = new Random();

    public ProduceAndConsumerDemo(int count) {
        this.count = count;
        items = new ArrayList<>(count);
    }


    Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                try {
                    if(size >= count){
                        System.out.println("队列满了，阻塞生产者！");
                        notFull.await();
                    }
                    ++size;
                    String msg = "item" + i;
                    items.add(msg);
                    System.out.println("生产者生产一条消息："+msg);
                    Thread.sleep(random.nextInt(1000));
                    notEmpty.signal();//唤醒消费者
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    },"thread-1");



    Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (;;){
                lock.lock();
                try {
                    if (items.isEmpty()){
                        System.out.println("队列空了，先阻塞消费者！");
                        notEmpty.await();
                    }
                    --size;
                    String item = items.remove(0);
                    System.out.println("消费者消费一条数据："+item);
                    Thread.sleep(random.nextInt(1000));
                    notFull.signal();//唤醒生产者
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    },"thread-2");


    public static void main(String[] args) throws InterruptedException {

        ProduceAndConsumerDemo demo = new ProduceAndConsumerDemo(10);

        demo.thread1.start();

        Thread.sleep(1000);

        demo.thread2.start();

    }
}
