package com.liangzc.example.thread.completableFuture;

import org.junit.Test;

import java.util.concurrent.*;

public class FutureTestDemo {


    class CallableExample implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "callable测试";
        }
    }

    @Test
    public void callableTest() throws ExecutionException, InterruptedException {
        CallableExample callableExample = new CallableExample();
        FutureTask<String> futureTask = new FutureTask(callableExample);
        new Thread(futureTask).start();
        System.out.println("--------callable执行---------");

        //阻塞获取返回信息
        String result = futureTask.get();
        System.out.println("获取的返回信息："+result);
    }


    /**
     *  runAsync:无返回值的异步执行
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void completableRunAsyncTest() throws ExecutionException, InterruptedException {
        //无返回值的,默认使用线程池为：ForkJoinPool
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("无返回值的执行，线程为："+Thread.currentThread().getName()));
        System.out.println(future.get());

        System.out.println("-------------------------------------------------------------------------------");
        //可自定义线程池去执行
        CompletableFuture.runAsync(() -> System.out.println("无返回值的执行,自定义线程池,线程为:"+Thread.currentThread().getName()), Executors.newFixedThreadPool(2));
    }

    /**
     *  supplyAsync：带返回值的异步执行
     */
    @Test
    public void completableSupplyAsyncTest() throws ExecutionException, InterruptedException {
        //带返回值的异步执行
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "带返回值的异步执行");
        System.out.println(stringCompletableFuture.get());

        System.out.println("----------------------------------------------------------------------------------");
        //thenAccept:纯消费，并无返回值
        CompletableFuture<Void> thenAccept = CompletableFuture.supplyAsync(() -> "带返回值的异步执行,并消费：accept").thenAccept(r -> System.out.println(r));
        System.out.println("thenAccept返回信息:"+thenAccept.get());

        System.out.println("----------------------------------------------------------------------------------");
        //thenApply:消费并返回
        CompletableFuture<String> thenApply = CompletableFuture.supplyAsync(() -> "带返回值的异步执行,消费并返回：thenApply").thenApply(r -> {
            return r;
        });
        System.out.println("thenApply返回信息:"+thenApply.get());

        System.out.println("----------------------------------------------------------------------------------");
        //thenRun:既不消费，也不返回
        CompletableFuture<Void> thenRunAsync = CompletableFuture.supplyAsync(() -> "带返回值的异步执行,不消费也不返回：thenRun").thenRunAsync(()-> System.out.println("既不消费，也不返回"));
        System.out.println("thenRunAsync返回信息"+thenRunAsync.get());
    }


}
