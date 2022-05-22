package com.liangzc.example.spring_demo.applicationAware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *   实现ApplicationContextAware接口，获得ApplicationContext(容器本身)
 */

public class ApplicationAwareDemo implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public ApplicationContext getApplicationContext(){

        System.out.println(" this applicationContext is:" + applicationContext);
        return this.applicationContext;
    }
}
