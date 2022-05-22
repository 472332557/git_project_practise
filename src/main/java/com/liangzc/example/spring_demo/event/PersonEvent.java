package com.liangzc.example.spring_demo.event;

import org.springframework.context.ApplicationEvent;

public class PersonEvent extends ApplicationEvent {
    public PersonEvent(Object source) {
        super(source);
    }


    private String name;
    private String age;


    public PersonEvent(Object source, String name, String age) {
        super(source);
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
