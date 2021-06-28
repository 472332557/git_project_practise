package com.liangzc.example.design_pattern.head_first.factory_model.factory_method;

public class Test {

    public static void main(String[] args) {


        PizzaStore pizzaStore = new ChengduPizzaStore();
        pizzaStore.orderPizza(SpicyGlutenPizza.class);

    }
}
