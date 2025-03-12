package com.liangzc.example.genericity;

/**
 * @Auther: liangzc
 * @Date: 2025/3/12 11:08
 * @Description:
 */
public class Person {

    private String name;
    private Integer age;
    private Double tall;
    private Boolean sex;

    /**
     * 方法的重载，show介绍这个人的信息
     * @param name
     */
    void show(String name){
        this.name = name;
        System.out.println(this.name);
    }
    void show(Integer age){
        this.age = age;
        System.out.println(this.age);
    }
    void show(Double tall){
        this.tall = tall;
        System.out.println(this.tall);
    }

    void show(Boolean sex){
        this.sex = sex;
        System.out.println(this.sex);
    }
}
