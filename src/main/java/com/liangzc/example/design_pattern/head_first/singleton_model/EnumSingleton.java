package com.liangzc.example.design_pattern.head_first.singleton_model;

/**
 * 枚举类单例，优点：
 *  1、无法通过反射的方式来获取实例对象，这是jdk底层就做了的限制
 *  2、线程无法破坏。在类声明时就已经创建好了，放进了一个容器中（Map集合中）
 *
 *  缺点：由于类声明时就创建了枚举单例，并且把它放进了容器，并且有多少放多少，这不就和饿汉式单例一样了吗，浪费内存
 *  所以spring借鉴了枚举的做法，实现了容器式单例（IOC容器单例模式）
 *
 */
public enum  EnumSingleton {

    INSTANCE();

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
