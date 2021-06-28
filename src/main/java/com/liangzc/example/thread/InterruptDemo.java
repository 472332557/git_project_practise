package com.liangzc.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程的正确中断方式，使用interrupt做一个标记，来判断是否中断该线程
 * 我们也可以自定义一个标记，来判断是否终止线程，但是interrupt方法还调用了os底层的unpark唤醒线程方法，所以使用interrupt的方式
 */
public class InterruptDemo {


    public static void main(String[] args) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                //Thread.currentThread().isInterrupted()默认为false
                while (!Thread.currentThread().isInterrupted()) {
                    i++;
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {//让线程复位，置为false，可以去响应中断请求
//                        e.printStackTrace();
                        Thread.currentThread().interrupt();//再次中断
                    }
                    System.out.println("当前执行次数：" + i);
                }

            }
        }, "thread_01");

        thread.start();
        try {
            //让main线程睡1秒，保证thread_01运行
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();//默认值修改为true

    }
}
