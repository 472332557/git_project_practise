package com.liangzc.example.thread.juc.countDownLatch;

import com.mchange.v2.lang.ThreadUtils;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static CountDownLatch countDownLatch = new CountDownLatch(3);


    static class Thread1 extends Thread{

        @Override
        public void run() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    static class Thread2 extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    static class Thread3 extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {


        Thread1 thread1 = new Thread1();
        thread1.start();

        Thread2 thread2 = new Thread2();
        thread2.start();

        Thread3 thread3 = new Thread3();
        thread3.start();


        countDownLatch.await();

        System.out.println("end");

    }


}
