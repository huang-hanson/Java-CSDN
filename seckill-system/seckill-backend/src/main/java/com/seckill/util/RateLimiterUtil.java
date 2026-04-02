package com.seckill.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * 限流工具类
 * 基于 Redis 滑动窗口实现
 */
@Component
public class RateLimiterUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    public RateLimiterUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 限流脚本（Lua 脚本保证原子性）
     */
    private static final String RATE_LIMIT_SCRIPT =
            "local key = KEYS[1] " +
            "local limit = tonumber(ARGV[1]) " +
            "local window = tonumber(ARGV[2]) " +
            "local now = tonumber(ARGV[3]) " +
            "local removed = redis.call('ZREMRANGEBYSCORE', key, 0, now - window) " +
            "local count = redis.call('ZCARD', key) " +
            "if count < limit then " +
            "    redis.call('ZADD', key, now, now .. ':' .. math.random(1, 100000)) " +
            "    redis.call('EXPIRE', key, window) " +
            "    return 1 " +
            "else " +
            "    return 0 " +
            "end";

    /**
     * 检查是否超过限流
     * @param key 限流 key（通常是 userId 或接口路径）
     * @param limit 限制次数
     * @param windowSeconds 时间窗口（秒）
     * @return true-未超过限流，false-超过限流
     */
    public boolean isAllowed(String key, int limit, int windowSeconds) {
        long now = System.currentTimeMillis();

        Object result = redisTemplate.execute(
                connection -> {
                    byte[] scriptKey = redisTemplate.getStringSerializer().serialize(key);
                    byte[] script = redisTemplate.getStringSerializer().serialize(RATE_LIMIT_SCRIPT);
                    byte[] limitArg = redisTemplate.getStringSerializer().serialize(String.valueOf(limit));
                    byte[] windowArg = redisTemplate.getStringSerializer().serialize(String.valueOf(windowSeconds));
                    byte[] nowArg = redisTemplate.getStringSerializer().serialize(String.valueOf(now));

                    return connection.scriptExecute(
                            script,
                            Collections.singletonList(scriptKey),
                            java.util.Arrays.asList(limitArg, windowArg, nowArg)
                    );
                }
        );

        return result != null && Long.parseLong(result.toString()) == 1;
    }

    /**
     * 简单限流（固定窗口）
     * @param key 限流 key
     * @param limit 限制次数
     * @param windowSeconds 时间窗口（秒）
     * @return true-未超过限流，false-超过限流
     */
    public boolean isAllowedSimple(String key, int limit, int windowSeconds) {
        String redisKey = "rate_limit:" + key;

        Long count = redisTemplate.opsForValue().increment(redisKey);
        if (count == null) {
            return false;
        }

        if (count == 1) {
            redisTemplate.expire(redisKey, windowSeconds, TimeUnit.SECONDS);
        }

        return count <= limit;
    }
}
