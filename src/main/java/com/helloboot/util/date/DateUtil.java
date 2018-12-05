package com.helloboot.util.date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lujianhao on 2018/3/5.
 */
public class DateUtil {
    public static final TimeZone TIME_ZONE_CHINA_SHANGHAI = TimeZone.getTimeZone("Asia/Shanghai");    // 中国上海时区
    public static final TimeZone TIME_ZONE_UTC = TimeZone.getTimeZone("UTC");                         // UTC标准时间

    public static final String DATE_FORMAT_PATTERN_INT = "yyyyMMdd";
    public static final String DATE_FORMAT_PATTERN_SHORT_M = "yyyy-MM";
    public static final String DATE_FORMAT_PATTERN_SHORT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_PATTERN_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";                  // UTC 格式
    public static final String DATE_FORMAT_PATTERN_MONTH_INT = "yyyyMM";
    public static final String DATE_FORMAT_PATTERN_HOUR_INT = "yyyyMMddHH";
    public static final String DATE_FORMAT_PATTERN_MINUTE_INT = "yyyyMMddHHmm";
    public static final String DATE_FORMAT_PATTERN_HTTP = "EEE, dd MMM yyyy HH:mm:ss z";

    public static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    private static final ThreadLocal<Map<String, SimpleDateFormat>> dateFormatCacheLocal = new ThreadLocal<>();


    public static SimpleDateFormat getDateFormat(String dateFormatPatternStr) {
        if (StringUtils.isEmpty(dateFormatPatternStr)) {
            return null;
        }
        Map<String, SimpleDateFormat> dateFormatCache = dateFormatCacheLocal.get();
        if (dateFormatCache == null) {
            dateFormatCache = new HashMap<>();
        }
        SimpleDateFormat simpleDateFormat = dateFormatCache.get(dateFormatPatternStr);
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(dateFormatPatternStr);
            dateFormatCache.put(dateFormatPatternStr, simpleDateFormat);
            dateFormatCacheLocal.set(dateFormatCache);
        }
        return simpleDateFormat;
    }

    /**
     * 获取更改时区后的日期
     *
     * @param date    日期
     * @param oldZone 旧时区对象
     * @param newZone 新时区对象
     * @return 日期
     */
    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        Date dateTmp = null;
        if (date != null) {
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
            dateTmp = new Date(date.getTime() - timeOffset);
        }
        return dateTmp;
    }

    /**
     * UTC时区时间获取中国标准日期
     *
     * @param date 日期
     * @return 日期
     */
    public static Date UTCToChinaShangHai(Date date) {
        return changeTimeZone(date, TIME_ZONE_UTC, TIME_ZONE_CHINA_SHANGHAI);
    }

    /**
     * 本地时区时间获取中国标准日期时间
     *
     * @param localTimeZone 本地特定时区
     * @return 日期
     */
    public static Date localToChinaShangHai(TimeZone localTimeZone) {
        Date now = new Date();
        if (localTimeZone == null) return now;
        return changeTimeZone(now, TIME_ZONE_CHINA_SHANGHAI, localTimeZone);
    }

    /**
     * 日期字符串转换为日期
     * <p/>
     * 以下可匹配格式
     * yyyyMMdd
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd'T'HH:mm:ss'Z'
     *
     * @param dateString 日期字符串
     * @return 转换失败返回 NULL
     */
    public static Date parse(String dateString) {
        if (StringUtils.isNotBlank(dateString)) {
            try {
                if (dateString.length() == DATE_FORMAT_PATTERN_INT.length()) {
                    return getDateFormat(DATE_FORMAT_PATTERN_INT).parse(dateString);
                }
                if (dateString.contains("T")) {
                    return getDateFormat(DATE_FORMAT_PATTERN_UTC).parse(dateString);
                }
                if (dateString.length() == DATE_FORMAT_PATTERN_DEFAULT.length()) {
                    return getDateFormat(DATE_FORMAT_PATTERN_DEFAULT).parse(dateString);
                }
                if (dateString.length() == DATE_FORMAT_PATTERN_SHORT.length()) {
                    return getDateFormat(DATE_FORMAT_PATTERN_SHORT).parse(dateString);
                }
                if (dateString.length() == DATE_FORMAT_PATTERN_SHORT_M.length()) {
                    return getDateFormat(DATE_FORMAT_PATTERN_SHORT_M).parse(dateString);
                }
                if (dateString.length() < DATE_FORMAT_PATTERN_DEFAULT.length()) {
                    return getDateFormat(DATE_FORMAT_PATTERN_DEFAULT.substring(0,
                            dateString.length())).parse(dateString);
                }
            } catch (ParseException ignored) {
                ignored.printStackTrace();
            }
        }

        return null;
    }

    public static Date parse(String dateString, String formatString) {
        if (StringUtils.isBlank(dateString) || StringUtils.isBlank(formatString)) {
            return null;
        }
        try {
            return (new SimpleDateFormat(formatString)).parse(dateString);
        } catch (ParseException ignored) {
        }
        return null;
    }

    public static String defaultFormat(Date date) {
        if (date == null) {
            return "";
        }
        return getDateFormat(DATE_FORMAT_PATTERN_DEFAULT).format(date);
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }
        return getDateFormat(format).format(date);
    }

    /**
     * 当天开始时间
     *
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        return todayStart.getTime();
    }

    /**
     * 当天终止时间
     *
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 获取前后几天时间
     *
     * @param date
     * @return
     */
    public static Date getBeforeOrAfterTime(Date date, int amount) {

        Calendar todayAfter = Calendar.getInstance();
        todayAfter.setTime(date);
        todayAfter.add(Calendar.DAY_OF_MONTH, amount);
        return todayAfter.getTime();
    }

    /**
     * 毫秒数时间单位适配(分钟级)
     *
     * @param millisecond
     * @return
     */
    public static String adaptMinutesTimeunit(long millisecond) {
        long days = millisecond / (1000 * 60 * 60 * 24);
        if (days > 0) {
            return days + "天" + adaptMinutesTimeunit(millisecond % (1000 * 60 * 60 * 24));
        }
        long hours = millisecond / (1000 * 60 * 60);
        if (hours > 0) {
            return hours + "小时" + adaptMinutesTimeunit(millisecond % (1000 * 60 * 60));
        }
        long minutes = millisecond / (1000 * 60);
        if (minutes > 0) {
            return minutes + "分";
        }
        return "";
    }

    public static Date getThisMonday() {
        return getMonday(new Date());
    }

    public static Date getMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.addDays(date, -1));

        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        return DateUtils.addDays(date, 1 - dayWeek);
    }

    public static Date getFirstDayInMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取某年第一天日期
     *
     * @param date
     * @return
     */
    public static Date getYearFirst(Date date) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 根据数字获取周序
     */
    private static Map<Integer, String> dayOfWeekStrMap = new HashMap<>();

    public static String getDayOfWeek(int dayOfWeek) {
        if (CollectionUtils.isEmpty(dayOfWeekStrMap)) {
            dayOfWeekStrMap.put(1, "周一");
            dayOfWeekStrMap.put(2, "周二");
            dayOfWeekStrMap.put(3, "周三");
            dayOfWeekStrMap.put(4, "周四");
            dayOfWeekStrMap.put(5, "周五");
            dayOfWeekStrMap.put(6, "周六");
            dayOfWeekStrMap.put(7, "周日");
        }
        return dayOfWeekStrMap.get(dayOfWeek);
    }

    public static boolean isSameWeek(Date date, Date another) {
        return DateUtils.isSameDay(getMonday(date), getMonday(another));
    }

    public static boolean isSameYear(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    /**
     * 毫秒数时间单位适配(秒级)
     *
     * @param millisecond
     * @return
     */
    public static String adaptSecondsTimeunit(long millisecond) {
        long minutes = millisecond / (1000 * 60);
        long second = (millisecond % (1000 * 60) / 1000);
        return minutes + "'" + (second < 10 ? "0" + second : second) + "\"";
    }

    public static String adaptVideoTimeunit(int timeSecond) {

        int hour = timeSecond / (60 * 60);
        int minutes = timeSecond % (60 * 60) / 60;
        int second = timeSecond % 60;
        if (hour >= 1) {
            return getVideoTimeStr(hour) + ":" + getVideoTimeStr(minutes) + ":" + getVideoTimeStr(second);
        } else {
            return getVideoTimeStr(minutes) + ":" + getVideoTimeStr(second);
        }
    }

    private static String getVideoTimeStr(int timeInt) {
        return timeInt < 10 ? "0" + timeInt : String.valueOf(timeInt);
    }


    /**
     * 获取时间格式化字符串
     *
     * @param date
     * @return
     */
    public static String getTimeStr(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = getDateFormat("HH:mm");
        if (DateUtils.isSameDay(date, new Date())) {
            if (System.currentTimeMillis() - date.getTime() <= 1000 * 60 * 60) {
                return dateFormat.format(date);
            }
            return "今天 " + dateFormat.format(date);
        }
        if (DateUtils.isSameDay(date, DateUtils.addDays(new Date(), -1))) {
            return "昨天 " + dateFormat.format(date);
        }
        if (DateUtil.isSameYear(date, new Date())) {
            dateFormat = getDateFormat("M月d日 HH:mm");
            return dateFormat.format(date);
        }
        dateFormat = getDateFormat("yyyy年M月d日 HH:mm");
        return dateFormat.format(date);
    }

    public static String getTimeStr(@NotNull Date date, @NotNull String pattern) {
        SimpleDateFormat simpleDateFormat = getDateFormat(pattern);
        if (simpleDateFormat == null) {
            return null;
        }
        return simpleDateFormat.format(date);
    }

    /**
     * 获取本月的天数
     *
     * @param date
     * @return
     */
    public static int getDaliyOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static long getDateDiff(Date date1, Date date2) {
        return Math.round((date2.getTime() - date1.getTime()) * 1.0 / (1000 * 3600 * 24));
    }


    /**
     * 时间分段
     *
     * @param hour
     * @return
     */
    public static List<Date> dateList(Integer hour) {
        Date date = getEndHour(new Date());
        List<Date> dateList = new LinkedList<>();
        for (int i = hour; i >= 0; i--) {
            dateList.add(new Date(date.getTime() - i * 60 * 60 * 1000));
        }
        return dateList;
    }

    private static Date getEndHour(Date date) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.add(Calendar.HOUR, 1);
        todayEnd.set(Calendar.MINUTE, 0);
        todayEnd.set(Calendar.SECOND, 0);
        todayEnd.set(Calendar.MILLISECOND, 0);
        return todayEnd.getTime();
    }

    public static void main(String[] args) {
        //System.out.println(getDateDiff(parse("2017-01-01 00:00:00"), parse("2017-01-30 23:59:59")));
        //System.out.println(dateList(12).size());
        System.out.println(DateFormatUtils.format(DateUtil.getEndHour(new Date()), DateUtil.DATE_FORMAT_PATTERN_DEFAULT));
    }
}
