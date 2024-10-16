package com.liangzc.example.jdk8.lambda;

/**
 * 函数式接口
 *
 * @param <A>
 * @param <B>
 */
@FunctionalInterface
interface Transform<A, B> {
    B transform(A a);

    public static void main(String[] args) {
        //传统方式实现接口
        Transform<String, Integer> transform = new Transform<String, Integer>() {
            @Override
            public Integer transform(String s) {
                return Integer.valueOf(s);
            }
        };
        System.out.println(transform.transform("1"));

        //lambda方式使用接口
        Transform<String, Integer> transform1 = s -> Integer.valueOf(s);
        System.out.println(transform1.transform("2"));


    }
}
