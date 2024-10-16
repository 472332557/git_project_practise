package com.liangzc.example.spring_demo.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LzcRequestMapping {
    String value() default "";
}
