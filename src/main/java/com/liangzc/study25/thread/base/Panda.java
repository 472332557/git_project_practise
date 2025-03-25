package com.liangzc.study25.thread.base;

/**
 * @Auther: liangzc
 * @Date: 2025/3/25 10:18
 * @Description:
 */
public class Panda implements Runnable{
    @Override
    public void run() {
        try {
            //睡眠1秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("我叫 花花 呀！");
    }
}


class RunTest{

    public static void main(String[] args) {
        new Thread(new Panda()).start();
        //这就是多线程异步的特征，虽然睡眠1s，但不阻塞主线程的执行
        System.out.println("主线程执行！");
    }
}
