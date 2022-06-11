package com.liangzc.example.spring_demo.moduledriver.annotationway;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationScheduleMain {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ScheduleConfiguration.class);
    }
}
