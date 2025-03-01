package com.liangzc.example.thread.communication;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 实现线程间的通信，wait/notify
 * 典型的例子就是：生产者和消费者模型
 */
public class Consumer implements Runnable {

    private Queue<String> queueMsg;

    public Consumer(Queue<String> queueMsg) {
        this.queueMsg = queueMsg;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queueMsg) {
                while (queueMsg.isEmpty()) {
                    try {
                        System.out.println("队列空了，消费者阻塞！");
                        queueMsg.wait();//一定会释放锁。sleep并不会释放锁，只是释放cpu资源
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者消费消息：" + queueMsg.remove());
                queueMsg.notify();//唤醒被阻塞的生产者去生产。唤醒的是基于当前锁等待下的线程，其他的线程并不会管
            }

        }
    }
}
