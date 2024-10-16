package com.liangzc.example.reflect_operate;

public class Dog implements Pet {

    private String food = "骨头！";

    private String hobbies = "跑步！";


    @Override
    public void eat() {
        System.out.println("小狗喜欢吃：" + food);
    }

    @Override
    public void hobby() {
        System.out.println("小狗喜欢玩：" + hobbies);
    }


    @Override
    public String toString() {
        return "Dog{" +
                "food='" + food + '\'' +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }

    public String getWord(String a, String b) {
        return a.toUpperCase() + "_" + b.toLowerCase();
    }
}
