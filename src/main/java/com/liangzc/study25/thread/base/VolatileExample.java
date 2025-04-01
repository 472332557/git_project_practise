package com.liangzc.study25.thread.base;

/**
 * @Auther: liangzc
 * @Date: 2025/4/1 11:28
 * @Description: 线程的可见性问题：多个线程加载了同一个共享变量，其中一个线程修改了变量的值，其他线程并没有感知到这个修改，还是使用原来的值。
 */
public class VolatileExample {
    // volatile关键字，可以解决线程的可见性和有序性问题
    public volatile static boolean flag = false;
    public static void main(String[] args) throws InterruptedException {

        /**
         * main线程修改了共享变量flag的值，但是线程THREAD-A没有及时感知到，所以线程THREAD-A还是使用原来的值，导致线程THREAD-A的逻辑错误,
         * 线程无法被终止。
         */
        new Thread(() -> {
            int i = 0;
            while (!flag){
                //do nothing
                i++;
            }
        },"THREAD-A").start();

        Thread.sleep(1000);
        flag = true;
        System.out.println("flag is "+flag);
    }
}
