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
        /**
         *  等待Thread1线程执行结束，如果没有执行结束，则阻塞main线程
         *  thread.join本质是：将该线程加入到调用者线程当中，使得加入的线程的执行结果对调用者线程可见；
         *  也就是：阻塞当前调用者线程，等待该加入的子线程执行结束，然后继续去执行调用的子线程。
         */
        thread1.join();
        thread2.start();


    }
}
