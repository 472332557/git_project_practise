package com.liangzc.example.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorExample {


    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors(), 10, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 1000; i++) {

            System.out.println(i);
            executorService.execute(new Task(i));
        }
        executorService.shutdown();
    }


    static class Task implements Runnable {

        private volatile Integer count;

        public Task(Integer count) {
            this.count = count;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":开始执行了！" + count);
        }
    }
}
