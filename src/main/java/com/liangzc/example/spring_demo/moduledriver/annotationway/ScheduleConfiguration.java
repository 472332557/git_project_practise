package com.liangzc.example.spring_demo.moduledriver.annotationway;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.liangzc.example.spring_demo.moduledriver")
@EnableScheduling
@Configuration
public class ScheduleConfiguration {


}
