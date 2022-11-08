package com.liangzc.example.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;

public class MatchDemo {


    public static void main(String[] args) {
        //创建一个集合
        List<String> list = new ArrayList<>();
        list.add("a1");list.add("a2");list.add("a3");list.add("b1");list.add("b2");list.add("b3");
        streamMatchTest(list);
    }

    private static void streamMatchTest(List<String> list){
        //流对象中只要有一个元素匹配就返回true
        boolean anyStartWithA = list.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println("集合中是否有以'a'来头："+ anyStartWithA);
        //流对象中每一个元素都匹配才返回true
        boolean allStartWithA = list.stream().allMatch(s -> s.startsWith("a"));
        System.out.println("集合中每一个都是以'a'开头："+ allStartWithA);
        //流对象中没有匹配时返回true
        boolean noneStartWithA = list.stream().noneMatch(s -> s.startsWith("c"));
        System.out.println("集合中没有以'c'开头："+ noneStartWithA);
    }
}
