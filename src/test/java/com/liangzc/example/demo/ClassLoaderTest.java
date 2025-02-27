package com.liangzc.example.demo;

import com.liangzc.example.FeeItemType;

public class ClassLoaderTest {

    public static void main(String[] args) {
        // 输出当前类的类加载器，即AppClassLoader
        System.out.println(new FeeItemType().getClass().getClassLoader());

        // 输出当前类的类加载器的父类加载器，即ExtClassLoader
        System.out.println(new FeeItemType().getClass().getClassLoader().getParent());

        // 输出当前类的类加载器的祖父类加载器，即BootstrapClassLoader
        // 由于BootstrapClassLoader是用C++实现的，并不是继承了java.lang.ClassLoader，所以这里返回null
        System.out.println(new FeeItemType().getClass().getClassLoader().getParent().getParent());

        // 输出String类的类加载器，由于String类是基本类，由BootstrapClassLoader加载，所以这里返回null
        System.out.println(new String().getClass().getClassLoader());
    }

}
