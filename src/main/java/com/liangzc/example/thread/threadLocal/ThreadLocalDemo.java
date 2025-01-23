package com.liangzc.example.thread.threadLocal;

import org.junit.Test;

/**
 * ThreadLocal保证线程安全，实现线程之间的隔离，互不影响
 */

public class ThreadLocalDemo {

    public static Integer count = 0;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Test
    public void baseTest() {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new ThreadDemo());
            threads[i].start();
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void threadLocalTest() {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new ThreadLocalClazz());
            threads[i].start();
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadDemo implements Runnable {

        @Override
        public void run() {
            count += 5;
            System.out.println("count 值为：" + count);
        }
    }


    static class ThreadLocalClazz implements Runnable {

        @Override
        public void run() {
            Integer count = threadLocal.get();
            count += 5;
            threadLocal.set(count);
            System.out.println("count 值为：" + threadLocal.get());
        }
    }

}
