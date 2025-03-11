package com.liangzc.example.genericity.retry;

/**
 * @Auther: liangzc
 * @Date: 2025/3/11 17:24
 * @Description:
 */
public interface Container<T>{

    void add(T item);

    T get(int index);
}
