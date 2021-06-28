package com.liangzc.example.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzDemoJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("现在开始吃包子："+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }
}
