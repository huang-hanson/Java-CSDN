package com.hanson.java.base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DateTest
 * @Description TODO
 * @date 2025/4/27 15:36
 **/
public class DateTest {

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        System.out.println(getTimeBucketKey(LocalDateTime.now(), 5));
        System.out.println(LocalDateTime.now().plusMinutes(13));
        System.out.println(getTimeBucketKey(LocalDateTime.now().plusMinutes(13), 5));
    }

    public static String getTimeBucketKey(LocalDateTime now, Integer minute) {
        if (minute == null) {
            minute = 5;
        }
        if (minute <= 0 || minute > 60) {
            minute = 5;
        }
        return now.truncatedTo(ChronoUnit.MINUTES)
                .withMinute(now.getMinute() / minute * minute) // 每5分钟归一
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")); // e.g., 202504271130
    }
}