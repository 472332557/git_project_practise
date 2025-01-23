package com.liangzc.example.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {

    public static void main(String[] args) throws SchedulerException {


        //构建任务
        JobDetail jobDetail = JobBuilder.newJob(QuartzDemoJob.class).
                withIdentity("job1", "group1").build();


        //构建触发器
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();


        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 1/1 * * * ? *")).build();

        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();


    }
}
