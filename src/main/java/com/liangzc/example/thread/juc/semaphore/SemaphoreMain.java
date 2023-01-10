package com.liangzc.example.thread.juc.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreMain {


    public static void main(String[] args) {

        /**
         *      信号量，我更倾向于称之为令牌，每个线程先要去获取令牌：semaphore.acquire()，
         *      才有执行权限，等处理完成释放令牌：semaphore.release()，其他线程再去获得令牌执行
         */

        Semaphore semaphore = new Semaphore(1);

        SemaphoreDemo1 semaphoreDemo1 = new SemaphoreDemo1("A", semaphore);
        SemaphoreDemo2 semaphoreDemo2 = new SemaphoreDemo2("B", semaphore);

    }
}
