package com.liangzc.example.web_start;

import java.util.concurrent.*;

public class SingleTest {


    private static final int MAXIMUM_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    public static final SingleTest instace = new SingleTest();

    private final ExecutorService executor = new ThreadPoolExecutor(MAXIMUM_POOL_SIZE, MAXIMUM_POOL_SIZE, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

    public static final ArrayBlockingQueue<String> transferQueue = new ArrayBlockingQueue<>(500000);
    private SingleTest() {

    }


    public ExecutorService getExecutor(){
        return executor;
    }

}
