package com.liangzc.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 单例类中，线程池对象也是单例的
 */
public class SingleExample {

    private static SingleExample instance;

    private static final SingleExample instance1 = new SingleExample();

    private SingleExample() {

    }

    private ExecutorService executor = new ThreadPoolExecutor(1, 2, 10, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.DiscardOldestPolicy());

    public static SingleExample getInstance() {
        if (SingleExample.instance == null) {
            SingleExample.instance = new SingleExample();
        }
        return SingleExample.instance;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public static void main(String[] args) {

        SingleExample instance = SingleExample.getInstance();
        SingleExample instance1 = SingleExample.getInstance();
        ExecutorService executor = instance.getExecutor();
        ExecutorService executor1 = instance1.getExecutor();

        System.out.println(instance == instance1);
        System.out.println(executor == executor1);

        ExecutorService executor2 = SingleExample.instance1.executor;
        ExecutorService executor3 = SingleExample.instance1.executor;

        System.out.println(executor2 == executor3);


    }


}
