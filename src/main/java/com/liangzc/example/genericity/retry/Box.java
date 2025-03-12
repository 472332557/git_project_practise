package com.liangzc.example.genericity.retry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Auther: liangzc
 * @Date: 2025/3/11 16:39
 * @Description: 泛型类
 */
public class Box<T> {
    private T content;

    public void setContent(T content){
        this.content = content;
    }

    public T getContent(){
        return content;
    }


    public static void main(String[] args) {
        // 创建一个存储字符串的Box
        Box<String> stringBox = new Box<>();
        stringBox.setContent("hello");
        String boxContent = stringBox.getContent();
        System.out.println(boxContent);

        System.out.println("-------------------------------------------");
        // 创建一个存储整数的Box
        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(123);
        System.out.println(integerBox.getContent());

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
}
