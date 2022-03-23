package com.liangzc.example.genericity.genericinterface;

public class GenericAudi implements GenericCar<String>{
    @Override
    public void call(String s) {
        System.out.println(s);
    }
}
