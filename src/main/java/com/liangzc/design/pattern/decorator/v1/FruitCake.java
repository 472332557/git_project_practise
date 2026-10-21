package com.liangzc.design.pattern.decorator.v1;

public class FruitCake extends BaseCake{

    @Override
    protected String msg() {
        return super.msg() + "+水果";
    }

    @Override
    protected int price() {
        return super.price() + 5;
    }
}
