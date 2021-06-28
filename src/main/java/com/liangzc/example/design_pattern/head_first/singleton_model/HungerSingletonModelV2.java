package com.liangzc.example.design_pattern.head_first.singleton_model;

/**
 * 这也是饿汉式单例的一种书写方式，和饿汉式单例效果一模一样
 */
public class HungerSingletonModelV2 {

    private static HungerSingletonModelV2 instance;

    static {
        instance = new HungerSingletonModelV2();
    }

    private HungerSingletonModelV2() {

    }

    public static HungerSingletonModelV2 getInstance(){
        return instance;
    }

}
