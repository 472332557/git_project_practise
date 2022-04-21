package com.liangzc.example.othertest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.util.StringUtils;

public class DateTimeUtils {


    public static final String YY_MM_DD = "yyyy-MM-dd";
    public static final String YYMMDD = "yyyyMMdd";
    public static final String YYMM = "yyyyMM";
    public static final String YY_MM = "yyyy-MM";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    private static final String[] MONTH_ZH = new String[]{"十二月", "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月"};
    private static final String ACCT_TIME_LIMET_BENGIN_MONTH = "201501";


    public static DateTime parseDate(String dateStr) {
        return parseDate(dateStr, DateTimeUtils.DateTimeFormatConstants.yy_mm_dd);
    }

    public static DateTime parseDate(String dateStr, DateTimeFormatter formatter) {
        return StringUtils.isEmpty(dateStr) ? null : DateTime.parse(dateStr, formatter);
    }


    public static String formatDate(DateTime dateTime, String format) {
        if (dateTime == null) {
            return "";
        } else {
            if (StringUtils.isEmpty(format)) {
                format = "yyyy-MM-dd";
            }

            return dateTime.toString(format);
        }
    }

    public static String addDateMonth(DateTime dateTime, int month, String format) {
        if (dateTime == null) {
            dateTime = nowDate();
        }

        dateTime = dateTime.minusMonths(month);
        return formatDate(dateTime, format);
    }

    public static DateTime nowDate() {
        return new DateTime(DateTimeZone.getDefault());
    }

    public static final class DateTimeFormatConstants {
        public static final DateTimeFormatter ymd = ymd();
        public static final DateTimeFormatter yy_mm_dd = ISODateTimeFormat.yearMonthDay();
        public static final DateTimeFormatter yymm = yymm();
        public static final DateTimeFormatter yy_mm = ISODateTimeFormat.yearMonth();

        public DateTimeFormatConstants() {
        }

        private static DateTimeFormatter ymd() {
            return ymd == null ? (new DateTimeFormatterBuilder()).append((new DateTimeFormatterBuilder()).appendYear(0, 4).toFormatter()).append((new DateTimeFormatterBuilder()).appendMonthOfYear(2).toFormatter()).append((new DateTimeFormatterBuilder()).appendDayOfMonth(2).toFormatter()).toFormatter() : ymd;
        }

        private static DateTimeFormatter yymm() {
            return yymm == null ? (new DateTimeFormatterBuilder()).append((new DateTimeFormatterBuilder()).appendYear(0, 4).toFormatter()).append((new DateTimeFormatterBuilder()).appendMonthOfYear(2).toFormatter()).toFormatter() : yymm;
        }
    }
}
