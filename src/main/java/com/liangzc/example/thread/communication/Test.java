package com.liangzc.example.thread.communication;

import java.util.LinkedList;
import java.util.Queue;

public class Test {

    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();
        int maxSize = 10;
        Produce produce = new Produce(queue, maxSize);
        Consumer consumer = new Consumer(queue);
        Thread produceThread = new Thread(produce);
        Thread consumerThread = new Thread(consumer);
        produceThread.start();
        consumerThread.start();

    }
}
