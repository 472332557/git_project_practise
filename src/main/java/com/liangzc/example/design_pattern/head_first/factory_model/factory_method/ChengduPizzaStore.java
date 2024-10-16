package com.liangzc.example.design_pattern.head_first.factory_model.factory_method;

/**
 * 实现自己的制作披萨的逻辑
 */
public class ChengduPizzaStore extends PizzaStore {

    @Override
    public Pizza createPizza(Class<? extends Pizza> clazz) {
        if (clazz != null) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
