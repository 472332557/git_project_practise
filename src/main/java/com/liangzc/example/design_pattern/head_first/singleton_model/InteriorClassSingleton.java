package com.liangzc.example.design_pattern.head_first.singleton_model;

/**
 * 静态内部类单例模式，可以使代码更加优雅，相比起双重检查锁式的单例模式，这种更加简洁、优雅
 * 原理是使用了java的特性，当初始化类时，不会初始化内部类，所以当使用全局访问点获取实例时，才会去初始化内部类
 * 缺点：以上的种种创建单例的方法，都能够被反射暴力破解
 */
public class InteriorClassSingleton {

    private InteriorClassSingleton() {
    }

    public static InteriorClassSingleton getInstance() {
        return InteriorClass.INTANCE;
    }


    private static class InteriorClass {
        private static final InteriorClassSingleton INTANCE = new InteriorClassSingleton();

    }

}
