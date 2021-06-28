package com.liangzc.example.juc.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreMain {


    public static void main(String[] args) {


        Semaphore semaphore = new Semaphore(1);

        SemaphoreDemo1 semaphoreDemo1 = new SemaphoreDemo1("A", semaphore);
        SemaphoreDemo2 semaphoreDemo2 = new SemaphoreDemo2("B", semaphore);

    }
}
