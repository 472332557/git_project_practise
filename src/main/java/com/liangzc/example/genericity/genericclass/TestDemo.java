package com.liangzc.example.genericity.genericclass;

public class TestDemo {

    public static void main(String[] args) {

        Student stu = new Student();
        stu.setName("lilili");
//        stu.setName(20);
        stu.print();

        Teacher teacher = new Teacher();
        teacher.setAge(20);
//        teacher.setAge("lilili");
        teacher.print();

        System.out.println("----------------------------------");


        GenericPerson<String> person = new GenericPerson<>();
        person.setParam("lilili");
        person.print();

        GenericPerson<Integer> person1 = new GenericPerson<>();
        person1.setParam(20);
        person1.print();

    }

}
