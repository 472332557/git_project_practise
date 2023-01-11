package com.liangzc.example.thread.juc.kit;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch =  new CountDownLatch(2);

        Thread thread1 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1开始执行---------");
            countDownLatch.countDown();

        },"thread-1");
        thread1.start();

        Thread thread2 = new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread2开始执行---------");
            countDownLatch.countDown();

        },"thread-2");

        thread2.start();
        //会阻塞main线程，直到countDownLatch.countDown()执行以后为为0，countDownLatch.countDown()每次减1
        countDownLatch.await();
        System.out.println("main 线程执行结束------------");




    }
}
