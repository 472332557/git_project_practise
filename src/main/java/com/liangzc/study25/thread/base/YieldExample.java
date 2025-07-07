package com.liangzc.study25.thread.base;

/**
 * @Auther: liangzc
 * @Date: 2025/3/27 18:03
 * @Description:
 */
public class YieldExample implements Runnable{

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName + " is running: " + i);
            if (i == 3) {
                System.out.println(threadName + " is yielding CPU...");
                Thread.yield(); // 提示当前线程让出 CPU
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new YieldExample(), "Thread-1");
        Thread t2 = new Thread(new YieldExample(), "Thread-2");

        t1.start();
        t2.start();
    }
}
