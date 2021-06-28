package com.liangzc.example.design_pattern.head_first;

public class LiveDuckFly implements DuckFlyHandler {
    @Override
    public void fly() {
        System.out.println("活的鸭子是会飞滴！");
    }
}
