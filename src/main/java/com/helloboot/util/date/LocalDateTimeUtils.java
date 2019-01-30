package com.helloboot.util.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * 时间工具类
 * Created by lujianhao on 2018/3/5.
 */

public class LocalDateTimeUtils {
    public static final String DATE_FORMAT_PATTERN_SHORT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_PATTERN_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_FORMAT_PATTERN_MONTH_INT = "yyyyMM";
    public static final String DATE_FORMAT_PATTERN_DAY_INT = "yyyyMMdd";
    public static final String DATE_FORMAT_PATTERN_HOUR_INT = "yyyyMMddHH";
    public static final String DATE_FORMAT_PATTERN_MINUTE_INT = "yyyyMMddHHmm";
    public static final String DATE_FORMAT_PATTERN_SECOND_INT = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_PATTERN_HTTP = "EEE, dd MMM yyyy HH:mm:ss z";

    /**
     * Date to LocalDateTime
     *
     * @param date the date
     * @return the localDateTime
     */
    public static LocalDateTime date2LocalDateTime(final Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime to Date
     *
     * @param time the localDateTime
     * @return the date
     */
    public static Date localDateTime2Date(final LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Timestamp to time string
     *
     * @param millis the timestamp
     * @return the time string
     */
    public static String millis2String(final long millis) {
        return millis2String(millis, DATE_FORMAT_PATTERN_DEFAULT);
    }

    /**
     * Timestamp to time string with the date format
     *
     * @param millis  the timestamp
     * @param pattern the date format
     * @return the time string
     */
    public static String millis2String(final long millis, final String pattern) {
        return localDateTime2String(millis2LocalDateTime(millis), pattern);
    }

    /**
     * Time string to timestamp
     *
     * @param timeStr the time string
     * @return the timestamp
     */
    public static long string2Millis(final String timeStr) {
        return string2Millis(timeStr, DATE_FORMAT_PATTERN_DEFAULT);
    }

    /**
     * Time string to timestamp with the date format
     *
     * @param timeStr the time string
     * @param pattern the date format
     * @return the timestamp
     */
    public static long string2Millis(final String timeStr, final String pattern) {
        return localDateTime2Millis(string2LocalDateTime(timeStr, pattern));
    }

    /**
     * LocalDateTime to time string
     *
     * @param localDateTime the localDateTime
     * @return the time string
     */
    public static String localDateTime2String(final LocalDateTime localDateTime) {
        return localDateTime2String(localDateTime, DATE_FORMAT_PATTERN_DEFAULT);
    }

    /**
     * LocalDateTime to time string
     *
     * @param localDateTime the localDateTime
     * @param pattern       the date format
     * @return the time string
     */

    public static String localDateTime2String(final LocalDateTime localDateTime, final String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Time string to LocalDateTime
     *
     * @param timeStr the time string
     * @return the localDateTime
     */
    public static LocalDateTime string2LocalDateTime(final String timeStr) {
        return string2LocalDateTime(timeStr, DATE_FORMAT_PATTERN_DEFAULT);
    }

    /**
     * Time string to LocalDateTime with the date format
     *
     * @param timeStr the time string
     * @param pattern the date format
     * @return the localDateTime
     */
    public static LocalDateTime string2LocalDateTime(final String timeStr, final String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(timeStr, df);
    }

    /**
     * Timestamp to LocalDateTime
     *
     * @param millis the timestamp
     * @return the localDateTime
     */
    public static LocalDateTime millis2LocalDateTime(final long millis) {
        return Instant.ofEpochMilli(millis)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * LocalDateTime to timestamp
     *
     * @param localDateTime the localDateTime
     * @return the timestamp
     */
    public static long localDateTime2Millis(final LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * Date plus a number, plus different values depending on the field
     * field is ChronoUnit.*
     *
     * @param time   the localDateTime
     * @param number the number
     * @param field  the ChronoUnit.*
     * @return the localDateTime
     */
    public static LocalDateTime plus(final LocalDateTime time, final long number, final TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * Date minu a number, minu different values depending on the field
     * field is ChronoUnit.*
     *
     * @param time   the localDateTime
     * @param number the number
     * @param field  the ChronoUnit.*
     * @return the localDateTime
     */
    public static LocalDateTime minu(final LocalDateTime time, final long number, final TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * Return the difference between two dates, different values depending on the field
     * field is ChronoUnit.*
     *
     * @param startTime the localDateTime
     * @param endTime   the localDateTime
     * @param field     the ChronoUnit.*
     * @return long
     */
    public static long betweenTwoTime(final LocalDateTime startTime, final LocalDateTime endTime, final ChronoUnit field) {
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
     * Return the current localDateTime
     *
     * @return the current localDateTime
     */
    public static LocalDateTime getNowLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Return the current date
     *
     * @return the current date
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * Return the current time in milliseconds
     *
     * @return the current time in milliseconds
     */
    public static long getNowMills() {
        return System.currentTimeMillis();
    }

    /**
     * Return the current formatted time string
     * The pattern is {@code yyyy-MM-dd HH:mm:ss}
     *
     * @return the current formatted time string
     */
    public static String getNowString() {
        return getNowString(DATE_FORMAT_PATTERN_DEFAULT);
    }

    /**
     * Return the current formatted time string
     *
     * @param pattern The format
     * @return the current formatted time string
     */
    public static String getNowString(final String pattern) {
        return millis2String(System.currentTimeMillis(), pattern);
    }

    /**
     * Return the start of the day
     *
     * @param dateTime the localDateTime
     * @return the start of the localDateTime
     */
    public static LocalDateTime getDateStart(final LocalDateTime dateTime) {
        return dateTime.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * Return the end of the day
     *
     * @param dateTime the localDateTime
     * @return the end of the localDateTime
     */
    public static LocalDateTime getDateEnd(final LocalDateTime dateTime) {
        return dateTime.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }

    private static final String[] CHINESE_ZODIAC =
            {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};

    /**
     * Return the Chinese zodiac.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the Chinese zodiac
     */
    public static String getChineseZodiac(final String time) {
        return getChineseZodiac(string2LocalDateTime(time, DATE_FORMAT_PATTERN_DEFAULT));
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param time       The formatted time string.
     * @param dateFormat The format.
     * @return the Chinese zodiac
     */
    public static String getChineseZodiac(final String time, final String dateFormat) {
        return getChineseZodiac(string2LocalDateTime(time, dateFormat));
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param localDateTime The LocalDateTime.
     * @return the Chinese zodiac
     */
    public static String getChineseZodiac(final LocalDateTime localDateTime) {
        return CHINESE_ZODIAC[localDateTime.getYear() % 12];
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param millis The milliseconds.
     * @return the Chinese zodiac
     */
    public static String getChineseZodiac(final long millis) {
        return getChineseZodiac(millis2LocalDateTime(millis));
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param year The year.
     * @return the Chinese zodiac
     */
    public static String getChineseZodiac(final int year) {
        return CHINESE_ZODIAC[year % 12];
    }

    private static final int[] ZODIAC_FLAGS = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};
    private static final String[] ZODIAC = {
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
            "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"
    };

    /**
     * Return the zodiac.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the zodiac
     */
    public static String getZodiac(final String time) {
        return getZodiac(string2LocalDateTime(time, DATE_FORMAT_PATTERN_DEFAULT));
    }

    /**
     * Return the zodiac.
     *
     * @param time       The formatted time string.
     * @param dateFormat The format.
     * @return the zodiac
     */
    public static String getZodiac(final String time, final String dateFormat) {
        return getZodiac(string2LocalDateTime(time, dateFormat));
    }

    /**
     * Return the zodiac.
     *
     * @param localDateTime The date.
     * @return the zodiac
     */
    public static String getZodiac(final LocalDateTime localDateTime) {
        return getZodiac(localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
    }

    /**
     * Return the zodiac.
     *
     * @param millis The milliseconds.
     * @return the zodiac
     */
    public static String getZodiac(final long millis) {
        return getZodiac(millis2LocalDateTime(millis));
    }

    /**
     * Return the zodiac.
     *
     * @param month The month.
     * @param day   The day.
     * @return the zodiac
     */
    public static String getZodiac(final int month, final int day) {
        return ZODIAC[day >= ZODIAC_FLAGS[month - 1]
                ? month - 1
                : (month + 10) % 12];
    }
}
