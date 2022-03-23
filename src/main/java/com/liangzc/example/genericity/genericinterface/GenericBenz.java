package com.liangzc.example.genericity.genericinterface;

/**
 * 泛型接口类的实现类，实现了泛型接口，并没有指定具体的类，同时实现类也并没有指定具体类型，还是泛型类
 * @param <T>
 */
public class GenericBenz<T> implements GenericCar<T>{

    @Override
    public void call(T t) {
        System.out.println(t);
    }

}
