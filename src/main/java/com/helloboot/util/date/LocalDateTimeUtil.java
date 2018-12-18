package com.helloboot.util.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * 时间工具类
 * Created by lujianhao on 2018/3/5.
 */

/**
 * check
 */
public class LocalDateTimeUtil {
    public static final String DATE_FORMAT_PATTERN_INT = "yyyyMMdd";
    public static final String DATE_FORMAT_PATTERN_SHORT_M = "yyyy-MM";
    public static final String DATE_FORMAT_PATTERN_SHORT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_PATTERN_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_FORMAT_PATTERN_MONTH_INT = "yyyyMM";
    public static final String DATE_FORMAT_PATTERN_HOUR_INT = "yyyyMMddHH";
    public static final String DATE_FORMAT_PATTERN_MINUTE_INT = "yyyyMMddHHmm";
    public static final String DATE_FORMAT_PATTERN_HTTP = "EEE, dd MMM yyyy HH:mm:ss z";

    /**
     * Date转换为LocalDateTime.
     *
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date.
     *
     * @param time
     * @return java.util.Date
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定日期的毫秒.
     *
     * @param time
     * @return java.lang.Long
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒.
     *
     * @param time
     * @return java.lang.Long
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式.
     *
     * @param time
     * @param pattern
     * @return java.lang.String
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取指定时间的默认格式.
     *
     * @param time
     * @return java.lang.String
     */
    public static String formatTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_DEFAULT));
    }

    /**
     * 获取当前时间的指定格式.
     *
     * @param pattern
     * @return java.lang.String
     */
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*.
     *
     * @param time
     * @param number
     * @param field
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*.
     *
     * @param time
     * @param number
     * @param field
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*.
     *
     * @param startTime
     * @param endTime
     * @param field
     * @return long
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12L + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间
     *
     * @param dateTime
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDateStart(LocalDateTime dateTime) {
        return dateTime.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间
     *
     * @param dateTime
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDateEnd(LocalDateTime dateTime) {
        return dateTime.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }
}
