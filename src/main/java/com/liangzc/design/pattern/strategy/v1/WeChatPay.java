package com.liangzc.design.pattern.strategy.v1;

public class WeChatPay implements Pay{
    @Override
    public void pay() {
        System.out.println("使用微信支付");
    }
}
