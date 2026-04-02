package com.seckill.aspect;

import com.seckill.common.Result;
import com.seckill.util.RateLimiterUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 限流切面
 * 用于拦截秒杀相关接口，防止恶意刷请求
 */
@Aspect
@Component
public class RateLimitAspect {

    @Autowired
    private RateLimiterUtil rateLimiterUtil;

    @Value("${seckill.rate-limit:10}")
    private int rateLimit;

    /**
     * 定义切点：所有 controller 包下的方法
     */
    @Pointcut("execution(* com.seckill.controller..*.*(..))")
    public void rateLimitPoint() {
    }

    /**
     * 环绕通知
     */
    @Around("rateLimitPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();

        // 只对秒杀相关接口进行限流
        if (!uri.contains("/seckill")) {
            return joinPoint.proceed();
        }

        // 获取用户标识（优先从 header 获取 userId，否则使用 IP）
        String userId = request.getHeader("X-User-Id");
        if (userId == null || userId.isEmpty()) {
            userId = getClientIp(request);
        }

        // 限流 key：seckill:rate_limit:{userId}
        String rateLimitKey = "seckill:rate_limit:" + userId;

        // 使用滑动窗口限流：每秒最多 10 次请求
        if (!rateLimiterUtil.isAllowed(rateLimitKey, rateLimit, 1)) {
            return Result.error("请求过于频繁，请稍后再试");
        }

        return joinPoint.proceed();
    }

    /**
     * 获取客户端 IP
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多个 IP，取第一个
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}
