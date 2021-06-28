package com.liangzc.example.spring_demo.annotation;

import java.lang.annotation.*;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@LzcComponent
public @interface LzcController {
    String value() default "";
}
