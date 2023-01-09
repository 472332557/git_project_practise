package com.liangzc.example.thread.juc.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo1 implements Runnable {

    private String name;

    private Semaphore semaphore;


    public SemaphoreDemo1(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
        new Thread(this).start();
    }

    @Override
    public void run() {

        try {
            Thread.sleep(10);
            semaphore.acquire();
            System.out.println(name + "获得线程执行权");

            for (int i = 0; i < 5; i++) {
                SemaphoreCount.count ++;
                System.out.println(name+":"+SemaphoreCount.count);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

    }
}
