package com.liangzc.example.reflect_operate;

/**
 * @Auther: liangzc
 * @Date: 2025/3/14 10:16
 * @Description:
 */
public class AnnotationDemo {

    @MyAnnotation(id = 1)
    public void say(String objects){
        System.out.println("hello world"+objects);
    }
}
