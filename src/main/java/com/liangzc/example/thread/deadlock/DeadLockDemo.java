package com.liangzc.example.thread.deadlock;

public class DeadLockDemo implements Runnable{

    private boolean flag;

    public DeadLockDemo(boolean flag) {
        this.flag = flag;
    }


    @Override
    public void run() {
        if (flag){
            while (!Thread.currentThread().isInterrupted()){
                synchronized (MyLock.object1){
                    System.out.println(Thread.currentThread().getName()+"======if 获得object1锁");
                    synchronized (MyLock.object2){
                        System.out.println(Thread.currentThread().getName()+"======if 获得object2锁");
                    }
                }
            }
        }else {
            while (!Thread.currentThread().isInterrupted()){
                synchronized (MyLock.object2){
                    System.out.println(Thread.currentThread().getName()+"======获得object2锁");
                    synchronized (MyLock.object1){
                        System.out.println(Thread.currentThread().getName()+"======获得object1锁");
                    }
                }
            }
        }
    }

}

