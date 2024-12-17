package com.hanson.java.base;

//import cn.hutool.core.date.StopWatch;
//import java.util.concurrent.TimeUnit;


import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StopWatch
 * @date 2024/12/17 19:34
 **/
public class StopWatchDemo {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch("stopwatch测试");
        stopWatch.start("第一阶段");
        Thread.sleep(1000);
        stopWatch.stop();

        stopWatch.start("第二阶段");
        Thread.sleep(2000);
        stopWatch.stop();

        stopWatch.start("第三阶段");
        Thread.sleep(500);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

//    public static void main(String[] args) throws InterruptedException {
//        long start = System.currentTimeMillis();
//        Thread.sleep(1000);
//        long end = System.currentTimeMillis();
//        System.out.println("耗时：" + (end - start) + "ms");
//    }

//    public static void main(String[] args) throws InterruptedException {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        Thread.sleep(1000);
//        stopWatch.stop();
//
//        // 输出结果
//        System.out.println("Total time: " + stopWatch.getTime() + " ms");
//    }

//    public static void main(String[] args) throws InterruptedException {
//        StopWatch stopWatch = new StopWatch();
//
//        // 开始计时
//        stopWatch.start();
//
//        // 第一阶段
//        Thread.sleep(1000);
//        stopWatch.split();
//        System.out.println("First part took: " + stopWatch.getSplitTime() + " ms");
//
//        // 第二阶段
//        Thread.sleep(2000);
//        stopWatch.split();
//        System.out.println("Second part took: " + stopWatch.getSplitTime() + " ms");
//
//        // 停止计时
//        stopWatch.stop();
//        System.out.println("Total time: " + stopWatch.getTime() + " ms");
//    }

//    public static void main(String[] args) throws InterruptedException {
//        StopWatch stopWatch = new StopWatch();
//
//        stopWatch.start("第一阶段");
//        Thread.sleep(1000);
//        stopWatch.stop();
//
//        stopWatch.start("第二阶段");
//        Thread.sleep(2000);
//        stopWatch.stop();
//
//        stopWatch.start("第三阶段");
//        Thread.sleep(500);
//        stopWatch.stop();
//
//        System.out.println(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));
//    }

}