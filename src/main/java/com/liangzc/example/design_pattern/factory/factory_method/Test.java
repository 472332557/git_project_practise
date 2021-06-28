package com.liangzc.example.design_pattern.factory.factory_method;

public class Test {

    public static void main(String[] args) {
        FactoryMethod factoryMethod = new JavaCourseFactory();
        ICourse iCourse = factoryMethod.createCourse();
        iCourse.explain();
    }
}
