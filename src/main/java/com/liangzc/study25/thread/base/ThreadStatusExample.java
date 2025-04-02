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
        /**
         * LOCK_OBJECT 被声明为 static final transient Object LOCK_OBJECT = new Object();。
         * static 关键字表示该对象属于类本身，而不是某个具体的实例。因此，所有 BlockDemo 类的实例共享同一个 LOCK_OBJECT 对象。
         * 当多个线程通过 synchronized (LOCK_OBJECT) 加锁时，实际上是对同一个对象加锁
         *
         */
        static final transient Object LOCK_OBJECT = new Object();
        @Override
        public void run() {
            /**
             * 在 run 方法中，使用了 synchronized (LOCK_OBJECT)，而 LOCK_OBJECT 是类级别的静态对象。
             * 当第一个线程进入 synchronized (LOCK_OBJECT) 块时，它会获取 LOCK_OBJECT 的锁。
             * 第二个线程尝试进入同一个 synchronized (LOCK_OBJECT) 块时，由于锁已经被第一个线程持有，它必须等待，直到第一个线程释放锁。
             * 因此，即使两个线程是基于不同的 BlockDemo 实例启动的，它们仍然会因为竞争同一个类级别的锁而发生阻塞。
             */
            synchronized (LOCK_OBJECT){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            // 锁的对象是this，所以两个线程可以同时执行，不同的对象实例，互不干扰
/*            synchronized (this){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }*/
        }
    }
}
