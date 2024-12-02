package com.hanson.java.generics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DateUtil
 * @Description TODO
 * @date 2024/11/28 17:41
 **/
public class DateUtil {
    public static void main(String[] args) {
        // 获取当前时间的 'yyyyMMddHHmmss' 格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTime = sdf.format(new Date());

        // 生成一个 6 位的随机数，范围 100000 到 999999
        Random random = new Random();
        int randomNum = 100000 + random.nextInt(900000); // 生成 6 位随机数

        // 拼接时间和随机数
        String checkid = currentTime + randomNum;

        // 输出生成的 checkid
        System.out.println("Generated checkid: " + checkid);
    }
}