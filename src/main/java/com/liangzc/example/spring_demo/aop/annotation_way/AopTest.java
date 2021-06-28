package com.liangzc.example.spring_demo.aop.annotation_way;

import com.liangzc.example.spring_demo.aop.annotation_way.basic.service.Animal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        Animal dog = applicationContext.getBean(Animal.class);

        dog.hobby();
    }

}
