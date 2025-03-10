package com.liangzc.example.extend;

/**
 * @Auther: liangzc
 * @Date: 2025/3/10 10:58
 * @Description:
 *  多态存在条件：
 *      1、具有继承的关系
 *      2、子类重写父类方法
 *      3、父类引用指向子类对象
 */
public class ExtendTest {

    public static void main(String[] args) {

        Student student = new Student();
        Person person = new Person();
        Person person1 = new Student();

        /**
         * 父类引用指向子类对象,如果子类重写了父类的方法，那么子类对象调用父类方法，会调用子类的方法
         *
         * 父类引用指向子类对象，如果子类没有重写父类的方法，那么子类对象调用父类方法，会调用父类的方法
         */
        student.eat();
        person.eat();
        person1.eat();

        /**
         * 子类对象调用子类方法，会调用子类的方法
         * 父类引用指向子类对象，子类单独的方法，父类无法调用子类的方法
         */
        student.study();
        //父类引用指向子类对象，子类单独的方法，父类无法调用子类的方法
//        person1.study();

    }
}
