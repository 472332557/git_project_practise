package com.liangzc.example.spring_demo.applicationAware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        ApplicationAwareDemo application = context.getBean("application", ApplicationAwareDemo.class);


        ApplicationContext context1 = application.getApplicationContext();

        System.out.println(context == context1);



    }
}
