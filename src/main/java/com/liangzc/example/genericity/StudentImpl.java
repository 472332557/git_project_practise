package com.liangzc.example.genericity;

/**
 * @Auther: liangzc
 * @Date: 2025/3/12 15:40
 * @Description:
 */
public class StudentImpl<T> implements PersonInterface<T>{

    private T t;

    public StudentImpl(T t) {
        this.t = t;
    }

    @Override
    public <T1> void task(T1 t1) {
        System.out.println("student's task ..."+t1);
        System.out.println(this.t);
    }
}
