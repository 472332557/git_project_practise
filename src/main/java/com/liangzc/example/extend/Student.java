package com.liangzc.example.extend;

/**
 * @Auther: liangzc
 * @Date: 2025/3/10 10:57
 * @Description:
 */
public class Student extends Person{
    @Override
    public void eat() {
        System.out.println("student eat");
    }

    public void study(){
        System.out.println("student study");
    }
}
