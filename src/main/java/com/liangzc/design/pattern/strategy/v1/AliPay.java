package com.liangzc.design.pattern.strategy.v1;

public class AliPay implements Pay{
    @Override
    public void pay() {
        System.out.println("使用支付宝支付");
    }
}
