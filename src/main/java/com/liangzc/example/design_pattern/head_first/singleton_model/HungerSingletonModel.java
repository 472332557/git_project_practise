package com.liangzc.example.design_pattern.head_first.singleton_model;

/**
 * 饿汉式单例，在类初始化时，就创建了实例对象，快速
 * 缺点：如果有大量的这样的类，有些可能也不需要这个实例对象，那会造成内存的浪费
 */
public class HungerSingletonModel {

    private static final HungerSingletonModel instance = new HungerSingletonModel();

    private HungerSingletonModel() {

    }

    public static HungerSingletonModel getInstance() {
        return instance;
    }
}
