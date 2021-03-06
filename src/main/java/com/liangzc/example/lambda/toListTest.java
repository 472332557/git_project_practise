package com.liangzc.example.lambda;

import com.alibaba.fastjson.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class toListTest {

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
        System.out.println(JSONArray.toJSONString(list));

        //倒叙
        List<Student> collect = list.stream().filter(e -> e.getGrade() == 2).sorted((x,z)-> z.getAge().compareTo(x.getAge())).collect(Collectors.toList());
        List<String> collect1 = list.stream().map(Student::getName).collect(Collectors.toList());

        System.out.println(collect);
        System.out.println(collect1);


        System.out.println(list.stream().map(v -> v.getAge()).max(Comparator.comparing(Integer::intValue)).get());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(format.format(date.getTime()));
        System.out.println(date.getDate());


        //求和
        System.out.println(list.stream().mapToInt(Student::getGrade).sum());


        //取最大值
        Date maxDate = list.stream().map(c -> c.getCreateDate()).max(Comparator.comparing(Date::getTime)).get();
        System.out.println(format.format(maxDate));

    }
}
