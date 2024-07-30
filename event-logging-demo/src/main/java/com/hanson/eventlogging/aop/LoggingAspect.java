package com.hanson.eventlogging.aop;

import io.micrometer.core.annotation.Timed;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName LoggingAspect
 * @Description TODO
 * @date 2024/7/30 11:14
 **/
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.hanson.eventlogging.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.trace("Entering method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @After("execution(* com.hanson.eventlogging.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.trace("Exiting method: {}", joinPoint.getSignature());
    }

    @Around("@annotation(timeout)")
    public Object around(ProceedingJoinPoint joinPoint, Timeout timeout) throws Throwable {
        long timeoutValue = timeout.value();
        long start = System.currentTimeMillis();

        // 使用一个线程来执行目标方法
        final Object[] result = {null};
        final Throwable[] exception = {null};
        Thread thread = new Thread(() -> {
            try {
                result[0] = joinPoint.proceed();
            } catch (Throwable throwable) {
                exception[0] = throwable;
            }
        });

        thread.start();
        thread.join(timeoutValue);

        if (thread.isAlive()) {
            thread.interrupt(); // 如果超时，打断线程
            throw new RuntimeException("Request timeout");
        }

        if (exception[0] != null) {
            throw exception[0];
        }

        return result[0];
    }
}