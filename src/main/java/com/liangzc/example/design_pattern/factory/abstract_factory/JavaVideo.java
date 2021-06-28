package com.liangzc.example.design_pattern.factory.abstract_factory;

public class JavaVideo implements IVideo {
    @Override
    public void replay() {
        System.out.println("提供了java录播功能！");
    }
}
