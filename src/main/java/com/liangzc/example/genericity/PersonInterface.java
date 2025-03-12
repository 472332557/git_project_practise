package com.liangzc.example.genericity;

/**
 * @Auther: liangzc
 * @Date: 2025/3/12 15:20
 * @Description:
 */
public interface PersonInterface<T> {

    <T> void task(T t);
}
