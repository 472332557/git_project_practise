package com.liangzc.example.spring_demo.beanAware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        PersonBeanAware personBeanAware = context.getBean(PersonBeanAware.class);

        personBeanAware.getBeanName();

    }
}
