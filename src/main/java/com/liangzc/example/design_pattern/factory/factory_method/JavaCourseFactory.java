package com.liangzc.example.design_pattern.factory.factory_method;

public class JavaCourseFactory implements FactoryMethod {
    @Override
    public ICourse createCourse() {
        return new JavaCourse();
    }
}
