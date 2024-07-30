package com.hanson.eventlogging.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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
}