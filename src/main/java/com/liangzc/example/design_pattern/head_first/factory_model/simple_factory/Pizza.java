package com.liangzc.example.design_pattern.head_first.factory_model.simple_factory;

public class Pizza {


    //准备
    public void prepare() {
        System.out.println("准备阶段。。。");
    }

    //烘烤
    public void bake() {
        System.out.println("烘烤阶段。。。");
    }

    //切片
    public void cut() {
        System.out.println("切片阶段。。。");
    }

    //包装
    public void box() {
        System.out.println("包装阶段。。。");
    }

}
