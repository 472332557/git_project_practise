package com.liangzc.example.thread.threadLocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
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

    // 非线程安全的，多线程环境下会发生异常
    private static  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

    // 使用ThreadLocal线程隔离维护DateFormat，保证线程安全
    public static DateFormat getFormat(){
        DateFormat dateFormat = dateFormatThreadLocal.get();
        if (dateFormat == null){
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        dateFormatThreadLocal.set(dateFormat);
        return dateFormatThreadLocal.get();
    }
    public static void parse(){
        try {
//            System.out.println(getFormat().parse("2021-01-01 23:23:18"));
            System.out.println(dateFormat.parse("2021-01-01 23:23:18"));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Test
    public void dateFormatTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    parse();
                }
            });
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
