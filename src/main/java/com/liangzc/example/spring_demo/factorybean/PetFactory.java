package com.liangzc.example.spring_demo.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 *  spring的工厂bean，这是一个特殊的bean，getBean并不会返回该工厂对象实例，而是返回该工厂对象中getObject()方法返回的对象。
 */
public class PetFactory implements FactoryBean<Pet>{

    @Override
    public Pet getObject() throws Exception {

        return new Cat();
    }

    @Override
    public Class<?> getObjectType() {
        return Cat.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
