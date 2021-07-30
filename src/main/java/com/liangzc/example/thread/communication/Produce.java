package com.liangzc.example.thread.communication;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 实现线程间的通信，wait/notify
 * 典型的例子就是：生产者和消费者模型，关键在于使用了共享的队列实现了线程的通信，通过共享队列加锁实现锁的抢占
 */
public class Produce implements Runnable{

    private Queue<String> queueMsg;
    private int maxSize;

    public Produce(Queue<String> queueMsg, int maxSize) {
        this.queueMsg = queueMsg;
        this.maxSize = maxSize;
    }


    @Override
    public void run() {
        int i = 0;

        while (true){
            synchronized (queueMsg){
                i ++;
                while (queueMsg.size() == maxSize){
                    try {
                        queueMsg.wait();//一定会释放锁。sleep并不会释放锁，只是释放cpu资源
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产消息："+i);
                queueMsg.add("生产消息："+i);
                queueMsg.notify();//唤醒被阻塞的消费者去消费。唤醒的是基于当前锁等待下的线程，其他的线程并不会管
            }

        }
    }
}
