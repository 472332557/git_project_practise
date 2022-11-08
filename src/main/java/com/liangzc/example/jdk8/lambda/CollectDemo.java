package com.liangzc.example.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectDemo {

    public static void main(String[] args) {

        //创建一个集合
        List<String> list = new ArrayList<>();
        list.add("a1");list.add("a2");list.add("a3");list.add("b1");list.add("b2");list.add("b3");

        list.stream().sorted().filter(e -> e.startsWith("b")).collect(Collectors.toList()).forEach(System.out::print);
    }

}
