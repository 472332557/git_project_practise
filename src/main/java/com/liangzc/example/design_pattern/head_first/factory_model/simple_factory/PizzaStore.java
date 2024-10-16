package com.liangzc.example.design_pattern.head_first.factory_model.simple_factory;

public class PizzaStore {

    SimplePizzaFactory simplePizzaFactory;

    public PizzaStore(SimplePizzaFactory simplePizzaFactory) {
        this.simplePizzaFactory = simplePizzaFactory;
    }

    public Pizza orderPizza(Class clazz) {

//        Pizza pizza = new SpicyGlutenPizza();
        Pizza pizza = simplePizzaFactory.createPizza(clazz);//使用简单工厂创建pizza实例
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
