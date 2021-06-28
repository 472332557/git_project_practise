package com.liangzc.example.design_pattern.head_first;

public class LiveDuckQuack implements DuckQuackHandler {
    @Override
    public void quack() {
        System.out.println("呱呱叫！");
    }
}
