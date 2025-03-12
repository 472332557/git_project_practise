package com.liangzc.example.genericity;

/**
 * @Auther: liangzc
 * @Date: 2025/3/12 11:42
 * @Description:
 */
public class PersonMethod {

    /**
     * 泛型方法
     * @param t 形参，可以是任意类型
     * @param <T> 类型参数，可以是任意类型
     */
    public <T> void show(T t){
        System.out.println(t);
    }
}
