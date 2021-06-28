package com.liangzc.example.design_pattern.head_first.factory_model.simple_factory;

public class Test {

    public static void main(String[] args) {
        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
        PizzaStore pizzaStore = new PizzaStore(simplePizzaFactory);
        pizzaStore.orderPizza(PotatoPizza.class);
    }
}
