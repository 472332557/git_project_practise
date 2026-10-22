package com.liangzc.design.pattern.strategy.v1;

//支付策略,供用户选择，在实际项目中，可以以此为入口，进行具体业务的选择
public class PayStrategy {

    private Pay pay;

    public PayStrategy(Pay pay) {
        this.pay = pay;
    }

    public void pay(){
        pay.pay();
    }

    public static void main(String[] args) {
//        //使用支付宝
//        Pay pay = new AliPay();
//        // 传入支付宝支付策略
//        PayStrategy payStrategy = new PayStrategy(pay);
//        payStrategy.pay();

        // 页面需要传递的标识
        Integer payType = 2;
        Pay pay = PayStrategyFactory.getPay(payType);
        pay.pay();
    }
}
