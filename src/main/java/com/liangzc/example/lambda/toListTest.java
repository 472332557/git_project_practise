package com.liangzc.example.lambda;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class toListTest {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Student student1 = new Student("lili",10,1);
        Student student2 = new Student("lucy",11,1);
        Student student3 = new Student("lei",12,1);
        Student student4 = new Student("mary",10,2);
        Student student5 = new Student("tom",9,2);
        Student student6 = new Student("jack",12,2);
        Student student7 = new Student("mali",10,3);
        Student student8 = new Student("kk",10,3);
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        list.add(student7);
        list.add(student8);
        System.out.println(JSONArray.toJSONString(list));

        List<Student> collect = list.stream().filter(e -> e.getGrade() == 2).sorted((x,z)-> z.getAge().compareTo(x.getAge())).collect(Collectors.toList());
        List<String> collect1 = list.stream().map(Student::getName).collect(Collectors.toList());

        System.out.println(collect);
        System.out.println(collect1);
    }
}
