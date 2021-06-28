package com.liangzc.example.design_pattern.factory.factory_method;

public class PythonCourseFactory implements FactoryMethod {
    @Override
    public ICourse createCourse() {
        return new PythonCourse();
    }
}
