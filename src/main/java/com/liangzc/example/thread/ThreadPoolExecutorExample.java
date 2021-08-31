package com.liangzc.example.thread;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {


    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = new ThreadPoolExecutor(4,
                Runtime.getRuntime().availableProcessors() * 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 1000; i++) {

            executorService.execute(new Task());
        }
        executorService.shutdown();
    }




    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":开始执行了！");
        }
    }
}
