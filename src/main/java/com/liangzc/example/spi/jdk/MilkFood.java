package com.liangzc.example.spi.jdk;

import com.liangzc.example.spi.Food;

public class MilkFood implements Food {
    @Override
    public void printName() {
        System.out.println("========================牛奶======================");
    }
}
