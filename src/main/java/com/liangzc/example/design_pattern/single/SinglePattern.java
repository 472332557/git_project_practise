package com.liangzc.example.design_pattern.single;

public class SinglePattern {

    private static SinglePattern instance;

    private SinglePattern() {

    }

    public static SinglePattern getInstance() {

        if (SinglePattern.instance == null) {
            SinglePattern.instance = new SinglePattern();
        }
        return SinglePattern.instance;
    }
}
