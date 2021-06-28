package com.liangzc.example.spring_demo.aop.annotation_way.basic.service.impl;

import com.liangzc.example.spring_demo.aop.annotation_way.basic.service.Animal;
import org.springframework.stereotype.Component;

@Component("aspectDog")
public class Dog implements Animal {
    @Override
    public void hobby() {
        System.out.println("小狗喜欢玩水！！！！");
    }
}
