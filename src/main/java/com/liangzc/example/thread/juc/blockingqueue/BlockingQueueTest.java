package com.liangzc.example.thread.juc.blockingqueue;

import java.util.Random;

public class BlockingQueueTest {

    public static void main(String[] args) {

        int count = 10;

        Random random = new Random();
        BlockingQueueExample bqe = new BlockingQueueExample(count);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                String item = "item-" + i;
                bqe.put(item);
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread thread2 = new Thread(() -> {
            for (; ; ) {
                bqe.take();
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();

        try {
            //让生产者线程先运行
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();


    }
}
