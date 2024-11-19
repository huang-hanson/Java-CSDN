package com.hanson.eventlogging.test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName LogTest
 * @Description 记录对比使用MessageFormat.format占位符的日志耗时
 * @date 2024/11/19 13:50
 **/
public class LoggingPerformanceTest {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(SLF4JPlaceholderExample.class);

    private static final int ITERATIONS = 10000000; // 迭代次数

    public static void main(String[] args) {
        logger.setLevel(Level.ERROR);

        // 测试 SLF4J 日志记录性能
        long slf4jTime = testSlf4jLogging();
        // 测试 MessageFormat 字符串格式化性能
        long messageFormatTime = testMessageFormatLogging();
        System.out.println("SLF4J占位符花费时间：" + slf4jTime + " 毫秒");
        System.out.println("MessageFormat格式花费的时间 " + messageFormatTime + " 毫秒");
    }

    // 实际的 SLF4J 日志记录方法
    private static long testSlf4jLogging() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < ITERATIONS; i++) {
            logger.info("SLF4J占位符输出这是数字: {}", i);
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    // 使用 MessageFormat 格式化字符串
    private static long testMessageFormatLogging() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < ITERATIONS; i++) {
            logger.info(MessageFormat.format("MessageFormat格式输出这是数字: {0}", i));
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}