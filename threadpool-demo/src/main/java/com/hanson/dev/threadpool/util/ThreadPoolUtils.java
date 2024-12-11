package com.hanson.dev.threadpool.util;

import com.hanson.dev.threadpool.constant.Constant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.concurrent.*;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ThreadPoolUtils
 * @date 2024/12/11 14:15
 **/
@Component
@Getter
@Slf4j
public class ThreadPoolUtils {

    /**
     * 线程池中常驻的线程数量
     */
    private final int corePoolSize = Constant.CORE_POOL_SIZE;

    /**
     * 线程池中的最大线程数量
     */
    private final int maximumPoolSize = 50;

    /**
     * 空闲的线程经过多长时间后会被销毁
     */
    private final long keepAliveTime = 60;

    /**
     * 时间单位
     */
    private final TimeUnit timeUnit = TimeUnit.SECONDS;

    /**
     * 任务队列，这里使用有界任务队列
     * 若有新的任务需要执行时，线程池会创建新的线程
     * 直到创建的线程数量达到corePoolSize时，则会将新的任务加入到等待队列中
     * 若等待队列已满，即超过ArrayBlockingQueue初始化的容量，则继续创建线程
     * 直到线程数量达到maximumPoolSize设置的最大线程数量
     * 若大于maximumPoolSize，则执行拒绝策略。
     */
    private final BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);

    /**
     * 线程工厂，用于创建线程，先用默认的
     */
    private final ThreadFactory threadFactory = Executors.defaultThreadFactory();

    /**
     * 拒绝策略，当任务太多来不及处理时，如何拒绝任务
     */
    private final RejectedExecutionHandler handler = (r, executor) -> {
        String logMessage = MessageFormat.format("SyncResumeTaskThreadPool执行了拒绝策略；当前活跃任务数量：{0}；当前任务总数：{1}；当前线程池容量：{2}；线程池最大容量：{3}",
                String.valueOf(executor.getActiveCount()),
                String.valueOf(executor.getTaskCount()),
                String.valueOf(executor.getPoolSize()),
                String.valueOf(executor.getMaximumPoolSize()));
        log.error(logMessage);
    };

    private final ExecutorService pool;

    public ThreadPoolUtils(){
        pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue, threadFactory, handler);
    }
}