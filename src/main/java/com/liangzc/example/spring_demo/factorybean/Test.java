package com.liangzc.example.spring_demo.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Pet cat = context.getBean("cat", Pet.class);

        System.out.println(cat.getName());

        Pet cat1 = context.getBean("cat", Pet.class);

        System.out.println(cat == cat1);

        //获得FactoryBean本身,在bean id前加&
        System.out.println(context.getBean("&cat"));


    }
}
