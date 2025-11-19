package com.liangzc.example.thread.threadLocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * ThreadLocal保证线程安全，实现线程之间的隔离，互不影响
 */

@Slf4j
public class ThreadLocalDemo {

    public static Integer count = 0;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public  static ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<>();

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

    public void parse(){
        DateFormat dateFormat = dateFormatThreadLocal.get();
        if (dateFormat == null){
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        dateFormatThreadLocal.set(dateFormat);
    }
    @Test
    public void dateFormatTest() throws InterruptedException {

//        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Thread.sleep(1000);
        for (int i = 0; i < 20; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        parse();
                        System.out.println(dateFormatThreadLocal.get().parse("2021-01-01 00:00:00"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
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
