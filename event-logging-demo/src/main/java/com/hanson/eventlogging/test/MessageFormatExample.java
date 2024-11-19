package com.hanson.eventlogging.test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MessageFormatExample
 * @Description 记录对比使用MessageFormat.format占位符的日志耗时
 * @date 2024/11/19 13:50
 **/
public class MessageFormatExample {
    private static final ch.qos.logback.classic.Logger log = (Logger) LoggerFactory.getLogger(SLF4JPlaceholderExample.class);

    public static void main(String[] args) {
        // 在程序运行时动态修改日志级别为 DEBUG
        log.setLevel(Level.WARN); // 设置日志级别为 DEBUG
        long start = System.currentTimeMillis();
        // 假设我们要记录一条包含一些复杂计算的日志
        for (int i = 0; i < 10000; i++) {  // 模拟多次日志记录
            // 即使日志级别是 INFO，MessageFormat.format 也会执行计算
            log.info(MessageFormat.format("The expensive calculation result is {0}", expensiveCalculation()));
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    // 假设的昂贵计算方法
    public static int expensiveCalculation() {
        try {
            // 模拟计算延迟
            Thread.sleep(1); // 1ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 42; // 返回一个常数，代表复杂计算的结果
    }
}