package com.liangzc.example.design_pattern.factory.abstract_factory;

public class PythonCourse implements ICourse {
    @Override
    public void explain() {
        System.out.println("这是Python课程!");
    }
}
