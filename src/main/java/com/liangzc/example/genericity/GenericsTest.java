package com.liangzc.example.genericity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Auther: liangzc
 * @Date: 2025/3/12 10:59
 * @Description:
 */
public class GenericsTest {

    // 为什么使用泛型？ 泛型的优点
    @Test
    public void advantage(){
        // TODO 2025/3/12 通过集合使用，确切感受泛型带来的优点
        System.out.println("-------------------集合未确定类型------------------------");

        //集合，未确定类型
        Collection list = new ArrayList();
        list.add("hello");
        list.add("Aiden");
        list.add(666);

        Iterator it = list.iterator();
        while (it.hasNext()){
            //强转为String类型
            String obj = (String) it.next();// ClassCastException,程序运行时，会发生类型转换异常
            System.out.println(obj);
        }

        System.out.println("-------------------集合确定类型------------------------");
        //集合确定类型，1、编译时，会进行类型检查。2、不会发生类型转换异常
        Collection<String> stringList = new ArrayList<>();
        stringList.add("hello");
        stringList.add("Aiden");
        stringList.add("666");
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()){
            //无需强转
            String obj = iterator.next();
            System.out.println(obj);
        }

        /**
         * 可以看出泛型的优点：
         *  1、将类型检查提前到编译时，减少运行时类型转换的开销及异常，提高程序性能。
         *  2、避免了类型转换的麻烦，使代码更加安全。
         */
    }

    // 方法重载
    @Test
    public void methodOverLoad(){
        //通过方法的重载介绍一个人的个人信息
        Person person = new Person();
        person.show("Aiden");
        person.show(28);
        person.show(175.5);
        person.show(true);
    }

    /**
     * 重载方法太多，多一个个人信息，就要多写一个重载方法，比较麻烦,使用泛型类
     */
    @Test
    public void genericClass(){
        System.out.println("---------------使用泛型类--------------------");
        GenericPerson<String> name = new GenericPerson<>();
        name.show("Aiden");
        GenericPerson<Integer> age = new GenericPerson<>();
        age.show(28);
        GenericPerson<Double> tall = new GenericPerson<>();
        tall.show(175.5);
        GenericPerson<Boolean> sex = new GenericPerson<>();
        sex.show(true);
    }

    /**
     * 使用泛型类后，确实不需要写很多的重载方法，直接使用一个方法即可，但是创建的泛型对象又太多，每一种类型，都要创建一个对象，还是麻烦。
     * 想要在方法中就确定了类型，使用泛型方法实现
     */
    @Test
    public void genericMethod(){
        System.out.println("---------------使用泛型方法--------------------");
        PersonMethod personMethod = new PersonMethod();
        personMethod.show("Aiden");
        personMethod.show(28);
        personMethod.show(175.5);
        personMethod.show(true);//男生
    }

    @Test
    public void genericInterface(){
        System.out.println("---------------使用泛型接口--------------------");
        PersonInterface student = new StudentImpl("xiao ming");
        student.task("study");
        PersonInterface teacher = new TeacherImpl("Ms.zhang");
        teacher.task("teach");


    }




}
