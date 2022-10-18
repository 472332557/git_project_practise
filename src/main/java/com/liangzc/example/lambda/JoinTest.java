package com.liangzc.example.lambda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JoinTest {

    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        Student student1 = new Student("lili",10,1,new Date());
        Student student2 = new Student("lucy",11,1,new Date());
        Student student3 = new Student("lei",12,1,new Date());
        Student student4 = new Student("mary",10,2,new Date());
        Student student5 = new Student("tom",9,2,new Date());
        Student student6 = new Student("jack",12,2,new Date());
        Student student7 = new Student("mali",10,3,new Date());
        Student student8 = new Student("kk",10,3,new Date());
        Student student9 = new Student("kk",20,3,new Date());
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        list.add(student7);
        list.add(student8);
        list.add(student9);

        joinTest(list);

    }

    public static void joinTest(List<Student> list){
        String names = list.stream().map(Student::getName).distinct().collect(Collectors.joining(","));
        System.out.println(names);
    }
}
