package com.liangzc.example.spring_demo.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventTest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        PersonEvent personEvent = new PersonEvent("", "小丽", "20");

        context.publishEvent(personEvent);

    }
}
