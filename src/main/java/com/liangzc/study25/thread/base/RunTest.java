package com.liangzc.study25.thread.base;

import org.junit.Test;

/**
 * @Auther: liangzc
 * @Date: 2025/3/25 11:17
 * @Description:
 */
public class RunTest {
    public static void main(String[] args) {
        new Thread(new Panda()).start();
        //这就是多线程异步的特征，虽然睡眠1s，但不阻塞主线程的执行
        System.out.println("主线程执行！");
    }

    @Test
    public void joinTest(){
        Thread thread = new Thread(new Panda());
        thread.start();
        try {
            /**
             * 阻塞主线程，直到thread线程执行完毕
             * thread线程的执行结果对主线程可见，也就是阻塞当前主线程，等待thread线程执行完毕
             */
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("主线程执行！");
    }
}
