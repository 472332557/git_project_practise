package com.liangzc.example.spring_demo.aop.annotation_way.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest {


    @Before("execution(* com.liangzc.example.spring_demo.aop.annotation_way.basic.service.impl.*.*(..))")
    public void startMethod(){

        System.out.println("执行前增强！！！！！！！！！！！！！");
    }

    @After("execution(* com.liangzc.example.spring_demo.aop.annotation_way.basic.service.impl.*.*(..))")
    public void endMethod(){
        System.out.println("执行后增强！！！！！！！！！！！！！！");
    }
}
