package com.helloboot;

import com.helloboot.util.date.LocalDateTimeUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.helloboot.util.date.LocalDateTimeUtils.DATE_FORMAT_PATTERN_DEFAULT;

/**
 * @author lujianhao
 * @date 2019/1/30
 */
public class LocalDateTimeUtilsTest {

    private LocalDateTime dateTime = LocalDateTime.of(2019, 1, 1, 0, 0, 0);
    private Date date = new Date(Long.valueOf("1546272000000"));
    private long timestamp = Long.valueOf("1546272000000");
    private String timeStr = "2019-01-01 00:00:00";

    @Test
    public void timeConvert() {
        Assert.assertEquals(
                LocalDateTimeUtils.date2LocalDateTime(date),
                dateTime
        );
        Assert.assertEquals(
                LocalDateTimeUtils.localDateTime2Date(dateTime),
                date
        );
        Assert.assertEquals(
                LocalDateTimeUtils.millis2String(timestamp),
                timeStr
        );
        Assert.assertEquals(
                LocalDateTimeUtils.string2Millis(timeStr),
                timestamp
        );
        Assert.assertEquals(
                LocalDateTimeUtils.localDateTime2String(dateTime),
                timeStr
        );
        Assert.assertEquals(
                LocalDateTimeUtils.string2LocalDateTime(timeStr),
                dateTime
        );
        Assert.assertEquals(
                LocalDateTimeUtils.millis2LocalDateTime(timestamp),
                dateTime
        );
        Assert.assertEquals(
                LocalDateTimeUtils.localDateTime2Millis(dateTime),
                timestamp
        );
    }

    @Test
    public void timeCalculate() {
        LocalDateTime localDateTime = LocalDateTime.of(2019,2,1,0,0,0);
        System.out.println(localDateTime);
        System.out.println(LocalDateTimeUtils.plus(localDateTime,1, ChronoUnit.YEARS));
        System.out.println(LocalDateTimeUtils.plus(localDateTime,1, ChronoUnit.MONTHS));
        System.out.println(LocalDateTimeUtils.plus(localDateTime,1, ChronoUnit.DAYS));
        System.out.println(LocalDateTimeUtils.plus(localDateTime,1, ChronoUnit.HOURS));
        System.out.println(LocalDateTimeUtils.plus(localDateTime,1, ChronoUnit.MINUTES));
        System.out.println(LocalDateTimeUtils.plus(localDateTime,1, ChronoUnit.SECONDS));

        System.out.println(LocalDateTimeUtils.minu(localDateTime,1, ChronoUnit.YEARS));
        System.out.println(LocalDateTimeUtils.minu(localDateTime,1, ChronoUnit.MONTHS));
        System.out.println(LocalDateTimeUtils.minu(localDateTime,1, ChronoUnit.DAYS));
        System.out.println(LocalDateTimeUtils.minu(localDateTime,1, ChronoUnit.HOURS));
        System.out.println(LocalDateTimeUtils.minu(localDateTime,1, ChronoUnit.MINUTES));
        System.out.println(LocalDateTimeUtils.minu(localDateTime,1, ChronoUnit.SECONDS));

        LocalDateTime localDateTime1 = LocalDateTime.of(2020,2,1,0,0,0);
        System.out.println(LocalDateTimeUtils.betweenTwoTime(localDateTime,localDateTime1, ChronoUnit.YEARS));
        System.out.println(LocalDateTimeUtils.betweenTwoTime(localDateTime,localDateTime1, ChronoUnit.MONTHS));
        System.out.println(LocalDateTimeUtils.betweenTwoTime(localDateTime,localDateTime1, ChronoUnit.DAYS));
        System.out.println(LocalDateTimeUtils.betweenTwoTime(localDateTime,localDateTime1, ChronoUnit.HOURS));
        System.out.println(LocalDateTimeUtils.betweenTwoTime(localDateTime,localDateTime1, ChronoUnit.MINUTES));
        System.out.println(LocalDateTimeUtils.betweenTwoTime(localDateTime,localDateTime1, ChronoUnit.SECONDS));
    }

    @Test
    public void timeNow() {
        System.out.println(LocalDateTimeUtils.getNowLocalDateTime());
        System.out.println(LocalDateTimeUtils.getNowDate());
        System.out.println(LocalDateTimeUtils.getNowMills());
        System.out.println(LocalDateTimeUtils.getNowString());
        System.out.println(LocalDateTimeUtils.getNowString(DATE_FORMAT_PATTERN_DEFAULT));
        System.out.println(LocalDateTimeUtils.getDateStart(LocalDateTime.now()));
        System.out.println(LocalDateTimeUtils.getDateEnd(LocalDateTime.now()));
    }
}
