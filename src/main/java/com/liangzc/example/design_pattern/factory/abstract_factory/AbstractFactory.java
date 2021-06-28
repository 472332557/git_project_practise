package com.liangzc.example.design_pattern.factory.abstract_factory;

/**
 * 抽象工厂，当工厂类具有共用的属性或者方法时，建议定义为抽象类，否则定义为接口
 */
public abstract class AbstractFactory {

    public void init(){
        System.out.println("抽象工厂初始化方法！");
    }

    public abstract INote createNote();

    public abstract IVideo createVideo();
}
