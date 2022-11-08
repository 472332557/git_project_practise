package com.liangzc.example.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountDemo {

    public static void main(String[] args) {

        //创建一个集合
        List<String> list = new ArrayList<>();
        list.add("a1");list.add("a2");list.add("a3");list.add("b1");list.add("b2");list.add("b3");

        long a1 = list.stream().filter(a -> a.startsWith("a")).sorted((x, z) -> z.compareTo(x)).count();
        System.out.println("总数为："+a1);

        List<String> a2 = list.stream().filter(a -> a.startsWith("a"))
                .map(String::toUpperCase)
                .sorted((x, z) -> z.compareTo(x))
                .collect(Collectors.toList());
        a2.forEach(System.out::print);
        
    }

}
