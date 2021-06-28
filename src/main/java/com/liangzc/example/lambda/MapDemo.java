package com.liangzc.example.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MapDemo {

    public static void main(String[] args) {
        //创建一个集合
        List<String> list = new ArrayList<>();
        list.add("a1");list.add("a2");list.add("a3");list.add("b1");list.add("b2");list.add("b3");
        streamMapTest(list);
    }

    private static void streamMapTest(List<String> list){
        list.stream().map(String::toUpperCase).sorted((s, t1) -> t1.compareTo(s)).forEach(System.out::println);
        System.out.println("- - - - - - ");
        //自定义映射规则
        Function<String,String> function = s -> {return  s + ".map3";};
        list.stream().map(function).forEach(System.out::println);
    }
}
