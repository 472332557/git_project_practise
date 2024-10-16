package com.liangzc.example.spring_demo.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 容器事件监听器，当applicationContext（容器）进行发布事件时（实现applicationEvent接口），
 * 通过publishEvent，会触发该监听器的监听事件。
 */
public class PersonApplicationListen implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        if (applicationEvent instanceof PersonEvent) {
            PersonEvent personEvent = (PersonEvent) applicationEvent;
            System.out.println("接收到personEvent事件：" + personEvent.getName() + ":" + personEvent.getAge());
        } else {
            System.out.println("容器事件：" + applicationEvent);
        }

    }
}
