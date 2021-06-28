package com.liangzc.example.thread;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class ThreadLifeCycleDemo implements Runnable{

    public static void main(String[] args) {

        Object object = new Object();
        new Thread(()->{
            try {
                while (true){
                    TimeUnit.SECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread-01").start();

        new Thread(()->{
            try {
                synchronized (ThreadLifeCycleDemo.class){
                    while (true){

                        ThreadLifeCycleDemo.class.wait();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread-02").start();

        ThreadLifeCycleDemo demo1 = new ThreadLifeCycleDemo();
        Thread thread1 = new Thread(demo1, "thread-03");
        Thread thread2 = new Thread(demo1, "thread-04");
        thread1.start();
        thread2.start();
    }


    @Override
    public void run() {
        synchronized (this){
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----------------------------");
        }
    }
}
