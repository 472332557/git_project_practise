package com.liangzc.example.design_pattern.head_first;

public class ToyDuckQuack implements DuckQuackHandler {
    @Override
    public void quack() {
        System.out.println("吱吱叫！");
    }
}
