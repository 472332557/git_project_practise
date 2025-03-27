package com.liangzc.study25.thread.base;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: liangzc
 * @Date: 2025/3/27 10:18
 * @Description:
 * 在 Java 中，每个线程都有一个布尔类型的中断状态（中断标志位），默认为 false。
 * 当调用 interrupt0() 时，会将该线程的中断标志位设置为 true，表示该线程已被请求中断。
 * 后续可以通过 isInterrupted() 或 Thread.interrupted() 方法检查该标志位的状态。
 */
public class InterruptExample {

    public static void main(String[] args) throws InterruptedException {
//        MyThread myThread = new MyThread();
//        myThread.start();
//        myThread.interrupt();


        MyThread2 myThread2 = new MyThread2();
        myThread2.start();
        TimeUnit.SECONDS.sleep(2);
        myThread2.interrupt();//将线程中断，状态设置为true
    }


    static class MyThread extends Thread{
        @Override
        public void run() {
            while (true){
                if(this.isInterrupted()){
                    System.out.println("线程被中断");
                    return;
                }
                System.out.println("线程正在运行");
            }
        }
    }


    static class MyThread2 extends Thread{
        @Override
        public void run() {
            System.out.println("线程开始执行时的中断状态："+Thread.currentThread().isInterrupted());
            while (!Thread.currentThread().isInterrupted()){
                try {
                    System.out.println("线程开始执行---");
                    /**
                     * 线程被睡眠了100s，此时想要中断该线程执行，所以主线程中调用了interrupt()方法
                     */
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    State state = Thread.currentThread().getState();
                    System.out.println(state.name());
                    System.out.println("线程复位状态："+Thread.currentThread().isInterrupted());
                    /**
                     * 此时，线程接受到中断状态，复位了，也就是从睡眠中复位了，此时中断状态为false，
                     * 但是，此时，是否决定中断该线程，是由开发人员决定的，所以再次调用interrupt()方法，会将中断状态设置为true，也就是再次中断了，
                     */
                    System.out.println("线程复位了，还需要你决定是否停止");
                    Thread.currentThread().interrupt();//重置中断状态为true
                    System.out.println("线程重置中断状态后："+Thread.currentThread().isInterrupted());
                }
                System.out.println("------------线程复位执行中");
            }
            System.out.println("线程执行完毕");
        }
    }
}
