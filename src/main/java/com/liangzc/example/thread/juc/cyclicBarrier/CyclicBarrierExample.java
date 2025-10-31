package com.liangzc.example.thread.juc.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier:
 *      CyclicBarrier 是一个同步工具类，它允许一组线程互相等待，直到到达某个公共屏障点。
 *      CyclicBarrier 的主要作用是让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 *      直到最后一个线程到达屏障时，屏障才会打开，所有被屏障阻塞的线程才会继续运行。
 *
 *      CyclicBarrier 的构造函数可以接受一个参数：
 *      int parties: 屏障的线程数
 *      Runnable barrierAction: 线程到达屏障时执行的动作
 *      当 CyclicBarrier 创建后，线程调用 await() 方法，就会进入等待状态，当所有线程都调用了 await() 方法，
 *      CyclicBarrier 就会打开，所有被屏障阻塞的线程就会继续运行。
 */
public class CyclicBarrierExample {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("CyclicBarrierExample:所有线程准备执行");
        }
    });
    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +":准备");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() +":开始执行");

            }
        }, "thread-01").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +":准备");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() +":开始执行");
            }
        }, "thread-02").start();
    }
}
