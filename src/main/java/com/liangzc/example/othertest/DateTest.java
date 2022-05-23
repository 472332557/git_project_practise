package com.liangzc.example.othertest;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {


    public static void main(String[] args) {
        DateTest.OwesCycleAge("2022-05");
//        System.out.println(lastDayOfMonth(new Date()));
//        getDateStr();
    }




    public static void OwesCycleAge(String statsMonth) {
        //参数statsMonth日期格式：yyyy-MM
        DateTime curDate = DateTimeUtils.parseDate(statsMonth + "-01");
        String curMonth = DateTimeUtils.formatDate(curDate, DateTimeUtils.YYMM);
        String oneMonth = DateTimeUtils.addDateMonth(curDate, 1, DateTimeUtils.YYMM);
        String twoMonth = DateTimeUtils.addDateMonth(curDate, 2, DateTimeUtils.YYMM);
        String threeMonth = DateTimeUtils.addDateMonth(curDate, 3, DateTimeUtils.YYMM);
        String sixMonth = DateTimeUtils.addDateMonth(curDate, 6, DateTimeUtils.YYMM);
        String oneYearMonth = DateTimeUtils.addDateMonth(curDate, 12, DateTimeUtils.YYMM);
        String twoYearMonth = DateTimeUtils.addDateMonth(curDate, 24, DateTimeUtils.YYMM);
        String threeYearMonth = DateTimeUtils.addDateMonth(curDate, 36, DateTimeUtils.YYMM);
        String fourYearMonth = DateTimeUtils.addDateMonth(curDate, 48, DateTimeUtils.YYMM);
        String fiveYearMonth = DateTimeUtils.addDateMonth(curDate, 60, DateTimeUtils.YYMM);
        String sevenYearMonth = DateTimeUtils.addDateMonth(curDate, 84, DateTimeUtils.YYMM);
        String tenYearMonth = DateTimeUtils.addDateMonth(curDate, 120, DateTimeUtils.YYMM);


        System.out.println("curMonth:"+Integer.valueOf(curMonth));
        System.out.println("oneMonth:"+Integer.valueOf(oneMonth));
        System.out.println("twoMonth:"+Integer.valueOf(twoMonth));
        System.out.println("threeMonth:"+Integer.valueOf(threeMonth));
        System.out.println("sixMonth:"+Integer.valueOf(sixMonth));
        System.out.println("oneYearMonth:"+Integer.valueOf(oneYearMonth));
        System.out.println("twoYearMonth:"+Integer.valueOf(twoYearMonth));
        System.out.println("threeYearMonth:"+Integer.valueOf(threeYearMonth));
        System.out.println("fourYearMonth:"+Integer.valueOf(fourYearMonth));
        System.out.println("fiveYearMonth:"+Integer.valueOf(fiveYearMonth));
        System.out.println("sevenYearMonth:"+Integer.valueOf(sevenYearMonth));
        System.out.println("tenYearMonth:"+Integer.valueOf(tenYearMonth));
    }

    public static final Date lastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    public static void getDateStr(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(lastDayOfMonth(new Date())));
    }
}
