package com.liangzc.example.genericity.genericclass;

/**
 * 泛型类格式：类名<T>，尖括号中是类型，可以是：T、E、K、V等，不重要，当做是一个形参就好（类比方法的形参），真正创建对象时给定具体的类型就好（好比方法的实参）
 * 也就是将类型参数化
 *
 * @param <T>
 */
public class GenericPerson<T> {

    private T param;

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public void print() {
        System.out.println(param);
    }
}
