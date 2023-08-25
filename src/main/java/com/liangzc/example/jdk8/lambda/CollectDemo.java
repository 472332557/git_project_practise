package com.liangzc.example.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectDemo {

    public static void main(String[] args) {

        //创建一个集合
        List<String> list = new ArrayList<>();
        list.add("a1");list.add("a2");list.add("a3");list.add("b1");list.add("b2");list.add("b3");

        list.stream().sorted().filter(e -> e.startsWith("b")).collect(Collectors.toList()).forEach(System.out::print);

        System.out.println("---------------------------------------------------");

        /**
         * Collectors.partitioningBy方法是java.util.stream.Collectors类中的一个静态方法，用于根据给定的条件将流中的元素分区为两个组。
         * partitioningBy方法返回一个Map，其中键是布尔值（true和false），表示两个组，值是满足相应条件的元素列表。
         */
        Map<Boolean, List<String>> partitioningCollect = list.stream().collect(Collectors.partitioningBy(e -> e.startsWith("a")));
        System.out.println(partitioningCollect.get(Boolean.TRUE));
        System.out.println(partitioningCollect.get(Boolean.FALSE));

    }

}
