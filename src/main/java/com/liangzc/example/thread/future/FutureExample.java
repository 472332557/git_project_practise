package com.liangzc.example.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureExample implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "异步取的返回值！";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureExample futureExample = new FutureExample();
        FutureTask<String> futureTask = new FutureTask<>(futureExample);
        new Thread(futureTask).start();

        System.out.println(futureTask.get());
    }


}
