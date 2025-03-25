package com.liangzc.study25.thread.base;

import org.junit.Test;

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
