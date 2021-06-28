package com.liangzc.example.design_pattern.factory.abstract_factory;

public class JavaNote implements INote {
    @Override
    public void edit() {
        System.out.println("提供了java笔记的功能！");
    }
}
