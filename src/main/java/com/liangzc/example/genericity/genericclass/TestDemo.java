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
        String param = person.getParam();
        System.out.println(param);
        person.print();

        GenericPerson<Integer> person1 = new GenericPerson<>();
        person1.setParam(20);
        Integer param1 = person1.getParam();
        System.out.println(param1);
        person1.print();

    }

}
