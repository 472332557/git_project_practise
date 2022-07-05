package com.liangzc.example.demo;

import java.util.*;
import java.util.stream.Collectors;

public class RandomTest {

    public static void main(String[] args) {

        System.out.println(new Random().nextInt(100));
        System.out.println(Math.round(Math.random() * 100));


        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        System.out.println(list.stream().collect(Collectors.joining(",")));

    }
}
