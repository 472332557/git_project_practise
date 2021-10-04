package com.liangzc.example.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample  implements Runnable{

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

    }

    @Override
    public void run() {
        lock.lock();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
