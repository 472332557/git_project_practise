package com.liangzc.example.design_pattern.proxy.staticproxy;

public class Test {

    public static void main(String[] args) {

        HouseAgent houseAgent = new HouseAgent(new ZhangSan());
        houseAgent.findHouse();
    }
}
