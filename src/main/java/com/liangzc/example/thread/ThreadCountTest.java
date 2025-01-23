package com.liangzc.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCountTest implements Runnable {

    private volatile Integer count = 0;

    public Integer add() {
        count++;
        return count;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer add = add();
            System.out.println("count = " + add);

            if (add == 50) {
                return;
            }
        }
    }


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new ThreadCountTest());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
