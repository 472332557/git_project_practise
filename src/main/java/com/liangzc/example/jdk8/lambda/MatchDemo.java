package com.liangzc.example.jdk8.lambda;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class MatchDemo {


    public static void main(String[] args) {
        //创建一个集合
        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        list.add("a3");
        list.add("b1");
        list.add("b2");
        list.add("b3");
        streamMatchTest(list);
    }

    private static void streamMatchTest(List<String> list) {
        //流对象中只要有一个元素匹配就返回true
        boolean anyStartWithA = list.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println("集合中是否有以'a'来头：" + anyStartWithA);
        //流对象中每一个元素都匹配才返回true
        boolean allStartWithA = list.stream().allMatch(s -> s.startsWith("a"));
        System.out.println("集合中每一个都是以'a'开头：" + allStartWithA);
        //流对象中没有匹配时返回true
        boolean noneStartWithA = list.stream().noneMatch(s -> s.startsWith("c"));
        System.out.println("集合中没有以'c'开头：" + noneStartWithA);
    }

    @Test
    public void test() {
        List<Student> list = new ArrayList<>();
        Student student1 = new Student("lili", 10, 1, new Date());
        Student student2 = new Student("lucy", 11, 1, new Date());
        Student student3 = new Student("lei", 12, 1, new Date());
        Student student4 = new Student("mary", 10, 2, new Date());
        Student student5 = new Student("tom", 9, 2, new Date());
        Student student6 = new Student("jack", 12, 2, new Date());
        Student student7 = new Student("mali", 10, 3, new Date());
        Student student8 = new Student("kk", 10, 3, new Date());
        Student student9 = new Student("kk", 20, 3, new Date());
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        list.add(student7);
        list.add(student8);
        list.add(student9);

        boolean anyMatch = list.stream().anyMatch(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getAge().compareTo(9) == 0;
            }
        });

        System.out.println(anyMatch);
    }


    @Test
    public void test1() {

        boolean isBig = BigDecimal.valueOf(10).compareTo(BigDecimal.valueOf(9)) > 0;
        System.out.println(isBig);
    }
}
