package com.liangzc.example.genericity.genericinterface;

public class TestDemo {

    public static void main(String[] args) {

        //实现类也并没有指定泛型类型，在创建实例对象时指定类型
        GenericCar<String> genericBenz = new GenericBenz();
        genericBenz.call("奔驰");

        GenericCar<Integer> genericBenz1 = new GenericBenz();
        genericBenz1.call(10);

        //这个是一个没有指定泛型的实现类，也可当做泛型类
        GenericBenz genericBenz2 = new GenericBenz();
        genericBenz2.call("奔驰（并未指定泛型）");
        genericBenz2.call(10);

        GenericAudi genericAudi = new GenericAudi();
        genericAudi.call("奥迪");

    }
}
