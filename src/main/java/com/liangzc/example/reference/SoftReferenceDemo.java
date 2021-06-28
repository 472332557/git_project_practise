package com.liangzc.example.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    public SoftReferenceDemo(Student student) {

    }

    public static void main(String[] args) {

        //使用Student实例，像这种把对象赋值给引用变量的，这个引用变量就是一个强引用
        Student student = new Student("lili", 20, "男");
        //设置为soft引用类型，并释放强引用
        SoftReference softReference = new SoftReference(student);
        student = null;

        if(softReference != null){
            student = (Student) softReference.get();
            student.setName("小明");
            System.out.println(student);
        }else {
            student = new Student();
            softReference = new SoftReference(student);
        }

    }


}
