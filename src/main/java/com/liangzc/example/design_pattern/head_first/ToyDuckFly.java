package com.liangzc.example.design_pattern.head_first;

public class ToyDuckFly implements DuckFlyHandler {
    @Override
    public void fly() {
        System.out.println("玩具鸭飞不起来滴！");
    }
}
