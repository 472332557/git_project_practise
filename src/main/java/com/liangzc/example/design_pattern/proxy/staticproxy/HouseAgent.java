package com.liangzc.example.design_pattern.proxy.staticproxy;

public class HouseAgent implements IPerson {

    private ZhangSan zhangSan;

    public HouseAgent(ZhangSan zhangSan) {
        this.zhangSan = zhangSan;
    }

    @Override
    public void findHouse() {
        zhangSan.findHouse();
    }
}
