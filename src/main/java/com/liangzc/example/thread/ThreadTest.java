package com.liangzc.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest extends Thread{

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("线程执行:"+Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {

        int threadCount = Runtime.getRuntime().availableProcessors();
        System.out.println(threadCount);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            ThreadTest threadTest = new ThreadTest();
            executorService.execute(threadTest);
        }
    }
}
