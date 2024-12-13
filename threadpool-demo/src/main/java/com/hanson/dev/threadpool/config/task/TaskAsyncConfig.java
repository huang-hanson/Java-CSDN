package com.hanson.dev.threadpool.config.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TaskAsyncConfig
 * @date 2024/12/13 14:24
 **/
@Configuration
@EnableAsync
public class TaskAsyncConfig {

    @Value("${task.pool.core-pool-size:2}")
    private int corePoolSize;

    @Value("${task.pool.max-pool-size:20}")
    private int maxPoolSize;

    @Value("${task.pool.queue-capacity:100}")
    private int queueCapacity;

    @Bean("testAsyncExecutor")
    public ThreadPoolTaskExecutor testAsyncExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("testAsyncExecutor--");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}