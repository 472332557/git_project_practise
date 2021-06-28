package com.liangzc.example.design_pattern.factory.simple;

public class JavaCourse implements Course {
    @Override
    public void explain() {
        System.out.println("这是java课程呀！");
    }
}
