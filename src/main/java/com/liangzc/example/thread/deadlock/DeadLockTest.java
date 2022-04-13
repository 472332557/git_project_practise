package com.liangzc.example.thread.deadlock;

public class DeadLockTest {

    public static void main(String[] args) {
        DeadLockDemo demo1 = new DeadLockDemo(true);
        DeadLockDemo demo2 = new DeadLockDemo(false);
        Thread thread1 = new Thread(demo1);
        Thread thread2 = new Thread(demo2);
        thread1.start();
        thread2.start();
    }
}
