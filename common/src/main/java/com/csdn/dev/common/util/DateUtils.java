package com.csdn.dev.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DateUtils
 * @Description TODO
 * @date 2025/6/16 18:15
 **/
public class DateUtils {


    public static final String DATE_STRING_PATTERN = "yyyyMMdd";

    /**
     * 获取当天的日期字符串，格式yyyyMM或者yyyyMMdd
     *
     * @return String
     */
    public static String getTodayDateString(String pattern) {
        LocalDateTime today = LocalDateTime.now();
        return today.format(DateTimeFormatter.ofPattern(pattern));
    }


}