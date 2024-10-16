package com.liangzc.example.design_pattern.factory.simple;

/**
 * 简单工厂模式，由工厂对象决定创建需要的对象实例
 * 创建对象的操作交由工厂类去处理，只需要传递一个参数即可
 * 缺点：工厂类职责相对过重，所有创建对象的操作都在一个工厂类中，增加新产品时需要修改工厂逻辑，违反开闭原则
 */
public class SimpleFactory {

    public JavaCourse createCourse(String name) {
        if ("java".equals(name)) {
            return new JavaCourse();
        }
        return null;
    }


    public JavaCourse createCourseByCLassName(String className) {

        if (!(className == null && "".equals(className))) {
            try {
                return (JavaCourse) Class.forName(className).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public JavaCourse createCourseByCLass(Class<? extends JavaCourse> clazz) {

        if (!(clazz == null && "".equals(clazz))) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
