package com.liangzc.example.design_pattern.factory.abstract_factory;

public class PythonVideo implements IVideo {
    @Override
    public void replay() {
        System.out.println("提供了python录播功能！");
    }
}
