package com.liangzc.example.design_pattern.factory.simple;

/**
 * 简单工厂模式，由工厂对象决定创建需要的对象实例
 */
public class Test {

    public static void main(String[] args) {

        //使用简单工厂设计模式，创建对象的操作交由工厂类去处理，只需要传递一个参数即可
        //v1
//        Course course = new JavaCourse();
        //v2
        Course course = new SimpleFactory().createCourse("java");//这样容易写错，改造下，传递class路径，通过反射获取
        course.explain();


        Course course1 = new SimpleFactory().createCourseByCLassName("org.example.design_pattern.factory.simple.JavaCourse");//这样还是有点麻烦，直接传递Class对象吧
        course1.explain();

        Course course2 = new SimpleFactory().createCourseByCLass(JavaCourse.class);//传递class对象
        course2.explain();
    }
}
