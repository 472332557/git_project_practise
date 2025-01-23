package com.liangzc.example.timeTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class TimeDemo {

    public static void main(String[] args) throws ParseException {

        Timer timer = new Timer(true);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String selectDate = "2020-11-08 11:13:00";
        Date date = simpleDateFormat.parse(selectDate);
        System.out.println(date);
        timer.schedule(new OwenTimeTask(), 1000, 1000);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }
}
