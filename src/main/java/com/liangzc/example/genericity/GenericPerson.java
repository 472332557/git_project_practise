package com.liangzc.example.genericity;

/**
 * @version 1.0
 * @Auther: liangzc
 * @Date: 2025/3/12 11:17
 * @Description: 泛型类
 */
public class GenericPerson<T> {

    /**
     *
     * @param t 泛型参数，可以是任意类型
     */
    public void show(T t){
        System.out.println(t);
    }
}
