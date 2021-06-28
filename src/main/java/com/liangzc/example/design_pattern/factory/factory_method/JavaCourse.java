package com.liangzc.example.design_pattern.factory.factory_method;

public class JavaCourse implements ICourse {
    @Override
    public void explain() {
        System.out.println("这是java课程！");
    }
}
