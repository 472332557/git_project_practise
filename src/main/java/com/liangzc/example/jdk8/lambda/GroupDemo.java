package com.liangzc.example.jdk8.lambda;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupDemo {

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


        Map<Integer, List<Student>> collect = list.stream().collect(Collectors.groupingBy(Student::getGrade));
        System.out.println("按班级汇总了："+collect);
        for (Map.Entry<Integer, List<Student>> listEntry : collect.entrySet()) {
            List<Student> value = listEntry.getValue();

            Map<Integer, List<Student>> collect1 = value.stream().collect(Collectors.groupingBy(Student::getAge));
            System.out.println("按年龄汇总了："+collect1);
            for (Map.Entry<Integer, List<Student>> entry : collect1.entrySet()) {
                System.out.println("key:"+entry.getKey());
                List<Student> value1 = entry.getValue();
                for (Student student : value1) {
                    System.out.println("student:"+student);
                }
            }

        }




    }
}
