package com.liangzc.example.spring_demo.moduledriver.annotationway;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  模块驱动的好处就是，需要什么组件时，不需要我们自己去声明、书写bean了，而是Spring帮我们将相关的bean依赖注入
 */

public class AnnotationScheduleMain {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ScheduleConfiguration.class);
    }
}
