package com.liangzc.example.design_pattern.design_principle.dependency_inversion;

public class JavaCourse implements Course {

    @Override
    public void explain() {

        System.out.println("学习java课程");
    }
}
