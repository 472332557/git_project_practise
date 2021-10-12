package com.liangzc.example.thread.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //构建CompletableFuture的两种方式：supplyAsync（） 和 runAsync（）
        //supplyAsync（）带返回值
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "dddddd");
        System.out.println(future.get());


        //runAsync（）无返回值
        CompletableFuture.runAsync(() -> System.out.println("无返回值的completableFuture！"));


        //accept：接收上一个任务的执行结果，但不返回
        CompletableFuture.supplyAsync(() -> "aaaa").thenAccept(x -> System.out.println(x + "bbbb"));

        //apply：接收上一个任务的执行结果，并返回
        CompletableFuture<String> thenApply = CompletableFuture.supplyAsync(() -> "aaaa").thenApply((x) -> x + "mmmm");
        System.out.println(thenApply.get());

        //run：不接受也不返回
        CompletableFuture.supplyAsync(() -> "aaaa").thenRun(() -> System.out.println("dsdsdsa"));



    }
}
