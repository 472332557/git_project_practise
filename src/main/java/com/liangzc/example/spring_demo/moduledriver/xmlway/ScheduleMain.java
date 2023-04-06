package com.liangzc.example.spring_demo.moduledriver.xmlway;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScheduleMain {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-moduledriver.xml");
        context.getBean("");
    }
}
