package com.liangzc.example.annotation;

public class AnnotationTest {

    public static void main(String[] args) {

        say();
    }


    @MethodAno(name = "first ano", numValue = 1)
    public static void say() {

        System.out.println("...........");
    }
}
