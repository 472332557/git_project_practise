package com.liangzc.example.genericity;

/**
 * @Auther: liangzc
 * @Date: 2025/3/12 15:46
 * @Description:
 */
public class TeacherImpl<T> implements PersonInterface<T> {


    private T t;

    public TeacherImpl(T t) {
        this.t = t;
    }

    @Override
    public <T1> void task(T1 t1) {
        System.out.println("teacher's task ..."+t1);
        System.out.println(this.t);
    }
}
