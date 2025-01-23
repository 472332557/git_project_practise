package com.liangzc.example.spring_demo.moduledriver;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduleDemo {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    @Scheduled(cron = "0/2 * * * * ?")
    public void start() {

        System.out.println("current time:" + dateFormat.format(new Date()));
    }
}
