package com.hanson.eventlogging.test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SLF4JPlaceholderExample
 * @Description 使用 SLF4J 占位符
 * @date 2024/11/19 13:51
 **/
public class SLF4JPlaceholderExample {
    private static final Logger log = (Logger) LoggerFactory.getLogger(SLF4JPlaceholderExample.class);

    public static void main(String[] args) {
        // 在程序运行时动态修改日志级别为 DEBUG
        log.setLevel(Level.WARN); // 设置日志级别为 DEBUG
        long start = System.currentTimeMillis();
        // 假设我们要记录一条包含一些复杂计算的日志
        for (int i = 0; i < 10000; i++) {  // 模拟多次日志记录
            // SLF4J 占位符：只有在日志级别启用时才会调用 expensiveCalculation()
            log.info("The expensive calculation result is {}", expensiveCalculation());
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