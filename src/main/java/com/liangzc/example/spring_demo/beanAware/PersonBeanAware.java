package com.liangzc.example.spring_demo.beanAware;

import org.springframework.beans.factory.BeanNameAware;

/**
 * 获取对应bean的id信息，也就是name是啥
 */
public class PersonBeanAware implements BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }


    public String getBeanName() {
        System.out.println("beanName is :" + beanName);
        return beanName;
    }
}
