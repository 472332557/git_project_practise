package com.liangzc.example.spring_demo.factorybean;

public class Cat implements Pet {
    @Override
    public String getName() {
        System.out.println("this is a lan mao!");
        return "lan_mao";
    }
}
