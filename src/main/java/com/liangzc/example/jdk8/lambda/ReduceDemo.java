package com.liangzc.example.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReduceDemo {

    public static void main(String[] args) {
        //创建一个集合
        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        list.add("a3");
        list.add("b1");
        list.add("b2");
        list.add("b3");
        streamReduceTest(list);

    }

    private static void streamReduceTest(List<String> list) {
        Optional<String> optional = list.stream().sorted().reduce((s, s2) -> {
            System.out.println(s + "-" + s2);
            return s + "-" + s2;
        });
    }
}
