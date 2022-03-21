package com.liangzc.example.demo;

import com.liangzc.example.FeeItemType;

public class ClassLoaderTest {

    public static void main(String[] args) {

        //App classLoader
        System.out.println(new FeeItemType().getClass().getClassLoader());

        //Ext classLoader
        System.out.println(new FeeItemType().getClass().getClassLoader().getParent());

        //bootStrap classLoader 返回为null，并不是没有bootStrap classLoader，而是这是c++代码实现的，并不是继承了java.lang.ClassLoader，所以返回为null
        System.out.println(new FeeItemType().getClass().getClassLoader().getParent().getParent());

        System.out.println(new String().getClass().getClassLoader());
    }

}
