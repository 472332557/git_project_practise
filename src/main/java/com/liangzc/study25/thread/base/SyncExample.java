package com.liangzc.study25.thread.base;

/**
 * @Auther: liangzc
 * @Date: 2025/3/28 10:43
 * @Description:
 */
public class SyncExample{

    public int count;
    // 同步方法，增加sync关键字，同一对象，互斥，同一时刻只能有一个线程执行该方法，知道该线程执行完，其他线程才能执行该方法
    public synchronized void incre(){
        count ++;
    }

    public static void main(String[] args) throws InterruptedException {
        SyncExample syncExample = new SyncExample();
        Thread thread01 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                syncExample.incre();
            }
        });
        Thread thread02 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                syncExample.incre();
            }
        });

        thread01.start();
        thread02.start();

        thread01.join();
        thread02.join();

        /**
         * 这里得到的结果不会按预想的是2000，结果不固定，因为count++不是原子性的
         *
         */
        System.out.println("count = " + syncExample.count);
    }
}
