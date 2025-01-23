package com.liangzc.example.design_pattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamichouseAgent implements InvocationHandler {

    private IPerson iPerson;

    public IPerson getInstance(IPerson iPerson) {
        this.iPerson = iPerson;
        Class<?> clazz = iPerson.getClass();
        ClassLoader classLoader = clazz.getClassLoader();
        Class<?>[] interfaces = clazz.getInterfaces();
        return (IPerson) Proxy.newProxyInstance(classLoader, interfaces, this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin------------------------------------");
        Object invoke = method.invoke(iPerson, args);
        System.out.println("end------------------------------------");
        return invoke;
    }
}
