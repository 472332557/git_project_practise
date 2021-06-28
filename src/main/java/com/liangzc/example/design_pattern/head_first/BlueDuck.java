package com.liangzc.example.design_pattern.head_first;

public class BlueDuck extends Duck {

    public BlueDuck(){
        //在这里具体确定，引入什么样的飞行动作和叫的动作
        duckFlyHandler = new LiveDuckFly();
        duckQuackHandler = new LiveDuckQuack();
    }

    @Override
    public void display() {
        System.out.println("是蓝鸭子呀！");
    }

    public static void main(String[] args) {

        BlueDuck blueDuck = new BlueDuck();
        blueDuck.swim();
        blueDuck.display();
        blueDuck.newFly();
        blueDuck.newQuack();
        blueDuck.setDuckFlyHandler(new ToyDuckFly());//可动态设置鸭子行为
        blueDuck.newFly();
    }

}
