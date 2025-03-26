package com.liangzc.study25.thread.base;

import java.util.concurrent.TimeUnit;

/**
 * 线程状态
 * NEW
 * RUNNABLE
 * BLOCKED
 * WAITING
 * TIMED_WAITING
 * TERMINATED
 */
public class ThreadStatusExample {

    /**
     * jps : 查看java进程列表 -l 可以查看示完整的主类名称或Jar包路径。
     * jstack : 查看线程状态，用于生成 Java 进程中每个线程的堆栈跟踪信息
     * @param args
     */
    public static void main(String[] args) {

        new Thread(()->{
            while (true){
                System.out.println("RUNNABLE");
            }
        },"RUNNABLE").start();

        new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"TIME_WATING").start();

        new Thread(()->{
            while (true){
                synchronized (ThreadStatusExample.class){
                    try {
                        ThreadStatusExample.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"WATING").start();


        new Thread(new BlockDemo(),"BLOCK_DEMO_01").start();
        new Thread(new BlockDemo(),"BLOCK_DEMO_02").start();

    }

    static class BlockDemo implements Runnable{

        @Override
        public void run() {
            synchronized (BlockDemo.class){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
