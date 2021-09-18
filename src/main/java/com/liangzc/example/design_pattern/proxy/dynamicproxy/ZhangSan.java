package com.liangzc.example.design_pattern.proxy.dynamicproxy;

public class ZhangSan implements IPerson {
    @Override
    public void findHouse() {
        System.out.println("张三找房子要求：安静、阳光充足！");
    }

    @Override
    public void hobbies() {
        System.out.println("张三喜欢喝可乐！");
    }
}
