package com.liangzc.example.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {


        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(500000);

        try {
            for (int i = 0; i < 1000; i++) {
                blockingQueue.put("--"+i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        System.out.println("size-------"+blockingQueue.size());
//        blockingQueue.put("-----11111");
        System.out.println("capacity-----"+blockingQueue.remainingCapacity());
    }

    //2147482647
}
