package com.liangzc.example.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompartorTest {


    public static void main(String[] args) {


        String a = "a";
        String b = "b";
        a.compareTo(b);

        List<Student> list = new ArrayList<>();
        Student stu1 = new Student("a",1);
        Student stu2 = new Student("b", 3);
        Student stu3 = new Student("c", 5);
        list.add(stu1);
        list.add(stu2);
        list.add(stu3);
        Collections.sort(list, new Comparator<Student>() {
            @Override
            //第一个参数.compareTo(第二个参数) 升序排列  o1.getAge().compareTo(o2.getAge()) 升序
            //第二个参数.compareTo(第一个参数) 降序排列  o2.getAge().compareTo(o1.getAge()) 降序
            public int compare(Student o1, Student o2) {
                return o1.getAge().compareTo(o2.getAge());//升序
            }
        });
        System.out.println(list);

        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getAge().compareTo(o1.getAge());//降序
            }
        });

        System.out.println(list);

    }
}
