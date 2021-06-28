package com.liangzc.example.design_pattern.head_first.singleton_model;

public class LazySingletonRunnable implements Runnable {
    @Override
    public void run() {
        LazySingletonModel instance = LazySingletonModel.getInstance();
        System.out.println(Thread.currentThread().getName() +"---instance:"+instance);
    }
}
