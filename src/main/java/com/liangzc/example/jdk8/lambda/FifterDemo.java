package com.liangzc.example.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FifterDemo {

    public static void main(String[] args) {

        //创建一个集合
        List<String> list = new ArrayList<>();
        list.add("a1");list.add("a2");list.add("a3");list.add("b1");list.add("b2");list.add("b3");
        sreamFilterTest(list);
    }

    public static void sreamFilterTest(List<String> lists){ //要明确这list的泛型类型，否则jvm不能根据上下文确定参数类型
        lists.stream().filter((s -> s.startsWith("a"))).forEach(System.out::print);//将开头是a的过滤出来
        System.out.println();

        //等价于以上操作
        Predicate<String> predicate = (s) -> s.startsWith("a");//将开头是a的过滤出来
        lists.stream().filter(predicate).forEach(System.out::print);
        System.out.println();

        //连续过滤
        Predicate<String> predicate1 = (s -> s.endsWith("1"));//将开头是a，并且结尾是1的过滤出来
        lists.stream().filter(predicate).filter(predicate1).forEach(System.out::print);
    }
}
