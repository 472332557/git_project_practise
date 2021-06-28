package com.liangzc.example.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class parallelStreamTest {

    public static void main(String[] args) {
        //创建一个大集合
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }
        parallelStreamSortedTest(list);
        streamSortedTest(list);
    }

    //并行stream
    private static void parallelStreamSortedTest(List<String> list){
        long startTime = System.nanoTime();//返回最准确的可用系统计时器的当前值，以毫微秒为单位。
        long count = list.parallelStream().sorted().count();
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.printf("并行排序花费时间：%d ms",millis);
    }
    //串行stream
    private static void streamSortedTest(List<String> list){
        long startTime = System.nanoTime();//返回最准确的可用系统计时器的当前值，以毫微秒为单位。
        long count = list.stream().sorted().count();
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.printf("串行排序花费时间：%d ms",millis);
    }
}
