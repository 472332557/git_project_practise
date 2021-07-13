package com.liangzc.example.thread.communication;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 实现线程间的通信，wait/notify
 * 典型的例子就是：生产者和消费者模型，关键在于使用了共享的队列实现了线程的通信
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
                        queueMsg.wait();
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
                queueMsg.notify();//唤醒被阻塞的消费者去消费
            }

        }
    }
}
