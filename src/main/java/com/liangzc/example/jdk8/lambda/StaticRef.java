package com.liangzc.example.jdk8.lambda;

public class StaticRef {

    public static void main(String[] args){
        //传统方式
        Transform<String ,Integer> transform1 = new Transform<String, Integer>() {
            @Override
            public Integer transform(String s) {
                return StaticRef.strToInt(s);
            }
        };
        int result1 = transform1.transform("100");
        System.out.println(result1);

        //Lambda方式
        Transform<String,Integer> transform2 = StaticRef ::strToInt;
        int result2 = transform2.transform("200");
        System.out.println(result2);
    }
    static int strToInt(String str){
        return Integer.valueOf(str);
    }

    @FunctionalInterface
    interface Transform<A,B>{
        B transform(A a);
    }
}
