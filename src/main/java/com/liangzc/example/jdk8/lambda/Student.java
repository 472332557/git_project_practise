package com.liangzc.example.jdk8.lambda;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Student {

    private String name;

    private Integer age;

    private Integer grade;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public Student(String name, Integer age, Integer grade, Date createDate) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.createDate = createDate;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", createDate=" + createDate +
                '}';
    }
}
