package com.liangzc.example.jdk8.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//排序
public class SortedTest {

    public static void main(String[] args) {

        //创建一个集合
        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        list.add("a3");
        list.add("b1");
        list.add("b2");
        list.add("b3");
        streamSortedTest(list);
    }

    private static void streamSortedTest(List<String> list) {
        //默认排序
        list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
        System.out.println("- - - - - - - - -");
        //自定义排序 倒叙排序
        list.stream().sorted((Comparator.reverseOrder())).filter(s -> s.startsWith("a")).forEach(System.out::println);

        System.out.println("- - - - - - - - -");
        //顺序排
        list.stream().sorted((String::compareTo)).filter(s -> s.startsWith("a")).forEach(System.out::println);
    }
}
