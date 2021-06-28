package com.liangzc.example.design_pattern.factory.abstract_factory;

public class PythonNote implements INote {
    @Override
    public void edit() {
        System.out.println("提供了python笔记的功能！");
    }
}
