package com.liangzc.example.design_pattern.head_first.factory_model.simple_factory;

public class SimplePizzaFactory {

    public Pizza createPizza(Class<? extends Pizza> pizzaClass) {
        if (pizzaClass != null) {
            try {
                return pizzaClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
