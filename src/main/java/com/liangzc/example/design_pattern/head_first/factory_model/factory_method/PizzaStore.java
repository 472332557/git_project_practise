package com.liangzc.example.design_pattern.head_first.factory_model.factory_method;

/**
 * 生意火爆，需要扩张了，引进了许多加盟店，比如：成都加盟店、上海加盟店、北京加盟店，此时又该怎么样处理呢？
 * 可以参考SimplePizzaFactory（简单工厂的模式），创建对应的上海加盟店工厂处理，这样可以做到，虽然统一做到了使用简单工厂来制造想要口味的pizza，但是，制作过程加盟店却使用了自己的一套
 * 你想要加盟店创建自己口味的披萨，但是却使用统一的制作过程，此时该怎么做呢？
 * 有个方法可以让制作过程局限于PizzaStore，但是加盟店可以制作自己口味的pizza，那就是把createPizza放回到PizzaStore当中,createPizza是一个抽象方法
 */
public abstract class PizzaStore {


    public Pizza orderPizza(Class<? extends Pizza> clazz) {

        Pizza pizza = createPizza(clazz);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(Class<? extends Pizza> clazz);

}
