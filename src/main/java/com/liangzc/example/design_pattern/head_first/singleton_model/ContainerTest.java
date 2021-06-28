package com.liangzc.example.design_pattern.head_first.singleton_model;

public class ContainerTest {

    public static void main(String[] args) {
        Object singleton = ContainerSingleton.getInstance("org.example.design_pattern.head_first.singleton_model.ContainerSingleton");
        System.out.println(singleton);

        Object singleton1 = ContainerSingleton.getInstance("org.example.design_pattern.head_first.singleton_model.ContainerSingleton");
        System.out.println(singleton1);

    }
}
