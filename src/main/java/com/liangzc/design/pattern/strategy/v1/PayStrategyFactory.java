package com.liangzc.design.pattern.strategy.v1;

import java.util.HashMap;
import java.util.Map;

public class PayStrategyFactory {
    /**
     * 使用策略模式，主要有以下几点：
     * 1、要设置策略标识，能够根据标识获取到具体的实现策略
     * 2、要提前将策略维护起来
     *
     * @return
     */

    public static Map<Integer, Pay> payMap = new HashMap<>();

    static {
        payMap.put(PayTypeEnum.ALI_PAY.getCode(), new AliPay());
        payMap.put(PayTypeEnum.WECHAT_PAY.getCode(), new WeChatPay());
    }

    private PayStrategyFactory () {

    }

    public static Pay getPay(Integer payType){
        return payMap.get(payType);
    }


}
