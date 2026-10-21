package com.liangzc.design.pattern.decorator.v1;

public class ChocolateAndFruitCake extends FruitCake{


    @Override
    protected String msg() {
        return super.msg() + "+巧克力";
    }

    @Override
    protected int price() {
        return super.price() + 10;
    }
}
