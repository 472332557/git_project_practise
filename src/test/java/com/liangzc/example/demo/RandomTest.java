package com.liangzc.example.demo;

import org.junit.Test;

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


        System.out.println(forTest());

    }


    public static String forTest(){
        for (int i = 0; i < 100; i++) {
            System.out.println("当前是："+i);
            if(i == 50){
                return "SUCCESS";
            }
        }
        return "END";
    }
}
