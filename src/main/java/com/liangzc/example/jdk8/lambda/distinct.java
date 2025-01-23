package com.liangzc.example.jdk8.lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Auther: liangzc
 * @Date: 2023/11/13 14:39
 * @Description:
 */
public class distinct {

    @Test
    public void method1() {
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
        List<Student> newStudents = new ArrayList<>(list.stream().collect(Collectors.toMap(Student::getName, Function.identity(), (existing, replacement) -> existing)).values());

        newStudents.forEach(System.out::println);
    }

    @Test
    public void method2() {
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

        Set<String> names = new HashSet<>();
        List<Student> students = list.stream().filter(e -> names.add(e.getName())).collect(Collectors.toList());
        students.forEach(System.out::println);
    }


    @Test
    public void method3() {
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

        List<Integer> list1 = Arrays.asList(9, 10);

        List<Student> collect = list.stream().filter(e -> !list1.contains(e.getAge())).collect(Collectors.toList());
        System.out.println(collect);
    }
}
