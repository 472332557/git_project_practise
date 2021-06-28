package com.liangzc.example.design_pattern.design_principle.dependency_inversion;

public class Test {

    /**
     * 依赖倒置原则：高层不应该依赖低层模块，对此来说Mary类就是低层，Test类调用就是高层
     * 现在需要随时增加课程，比如python、AI、大数据等
     * 我们肯定不能再去Mary类中再去增加，而是采用面向接口编程，以后无论增加什么课程只要实现Course接口即可
     *
     * @param args
     */
    public static void main(String[] args) {

        //V1
        Mary mary = new Mary();
        mary.study();

        //V2
        mary.study(new JavaCourse());

        //v3 构造注入
        Mary mary1 = new Mary(new JavaCourse());
        mary1.study1();

        //v4 设值注入
        mary.setCourse(new JavaCourse());
        mary.study1();
    }

}
