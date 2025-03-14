package com.liangzc.example.reflect_operate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: liangzc
 * @Date: 2025/3/14 10:02
 * @Description:
 */
// @Target：指定注解使用的范围
@Target({ElementType.TYPE, ElementType.METHOD})
/**
 * @Retention：指定注解的保留范围
 * 注解保留策略（Annotation Retention Policy）描述了注解在程序生命周期中的保留时间。通过 Retention 元注解，可以指定注解的保留策略。以下是常见的保留策略枚举值及其含义：
 * SOURCE：注解仅在源代码中保留，在编译时会被丢弃，不会进入类文件。
 * CLASS：注解会保留在类文件中，但在运行时不可用（默认值）。
 * RUNTIME：注解会保留在类文件中，并且在运行时可以通过反射访问。
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    //这些都是参数，而不是方法，切记
    String name() default "liangzc";
    //如果这里没有给定默认值，那么在使用的时候，必须给定一个值
    int id();
}
