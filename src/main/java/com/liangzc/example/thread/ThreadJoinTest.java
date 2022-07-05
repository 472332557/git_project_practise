package com.liangzc.example.thread;

public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(()->{

            try {
                Thread.sleep(5000);
                System.out.println("thread 1 执行-");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread thread2 = new Thread(()->{
            try {
                Thread.sleep(500);
                System.out.println("thread 2 执行-");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        //等待Thread1线程执行结束，如果没有执行结束，则阻塞main线程
        thread1.join();
        thread2.start();


    }
}
