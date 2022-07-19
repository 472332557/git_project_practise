package com.liangzc.example.dateTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String early = "2022-07-18 14:23:25";
        try {
            Date earlyDate = format.parse(early);
            System.out.println(hoursBetween(earlyDate, new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        hoursBetweenNew();


    }


    public static final int hoursBetween(Date early, Date late) {
        java.util.Calendar calst = java.util.Calendar.getInstance();
        java.util.Calendar caled = java.util.Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //得到两个日期相差的分钟数
        int hours = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 60 / 60;

        return hours;
    }



    public static final int hoursBetweenNew() {

        LocalDateTime now = LocalDateTime.now();

        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        String dateString = "2022-07-19 07:30:20";
        LocalDateTime earlyDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Duration duration = Duration.between(earlyDate,now);
        long toHours = duration.toHours();
        System.out.println("相差小时时间："+toHours);

        long toMinutes = duration.toMinutes();
        System.out.println("相差分钟时间："+toMinutes);

        return 1;
    }


}
