package com.liangzc.example.design_pattern.head_first.singleton_model;

import com.liangzc.example.netty.protocol.ReqTypeE;

public class Test {

    public static void main(String[] args) {


/*        new Thread(new Runnable() {
            @Override
            public void run() {
                LazySingletonModel instance = LazySingletonModel.getInstance();
                System.out.println(instance);
            }
        },"thread-01").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                LazySingletonModel instance = LazySingletonModel.getInstance();
                System.out.println(instance);
            }
        },"thread2-02").start();*/

        Thread thread1 = new Thread(new LazySingletonRunnable());
        Thread thread2 = new Thread(new LazySingletonRunnable());
        thread1.start();
        thread2.start();

        System.out.println(ReqTypeE.PING.toString());

    }
}
