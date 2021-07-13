package com.liangzc.example.thread.communication;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 实现线程间的通信，wait/notify
 * 典型的例子就是：生产者和消费者模型
 */
public class Consumer implements Runnable{

    private Queue<String> queueMsg;

    public Consumer(Queue<String> queueMsg) {
        this.queueMsg = queueMsg;
    }

    @Override
    public void run() {
        while (true){
            synchronized (queueMsg){
                while (queueMsg.isEmpty()){
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
                System.out.println("消费者消费消息："+queueMsg.remove());
                queueMsg.notify();//唤醒被阻塞的生产者去生产
            }

        }
    }
}
