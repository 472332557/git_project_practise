package com.liangzc.example.spring_demo.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        PetFactory petFactory = context.getBean(PetFactory.class);

        System.out.println(petFactory);

        Pet object = petFactory.getObject();

        object.getName();

    }
}
