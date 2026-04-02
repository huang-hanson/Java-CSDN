package com.seckill.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 分布式锁工具类
 * 基于 Redisson 实现
 */
@Component
public class DistributedLockUtil {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 尝试获取锁
     * @param lockKey 锁的 key
     * @param waitTime 等待时间（秒）
     * @param leaseTime 锁持有时间（秒），-1 表示不自动释放
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            if (leaseTime == -1) {
                return lock.tryLock(waitTime, TimeUnit.SECONDS);
            }
            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /**
     * 释放锁
     * @param lockKey 锁的 key
     */
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * 执行带锁的操作
     * @param lockKey 锁的 key
     * @param waitTime 等待时间（秒）
     * @param leaseTime 锁持有时间（秒）
     * @param action 要执行的操作
     * @param <T> 返回值类型
     * @return 操作结果
     */
    public <T> T executeWithLock(String lockKey, long waitTime, long leaseTime, Supplier<T> action) {
        if (tryLock(lockKey, waitTime, leaseTime)) {
            try {
                return action.get();
            } finally {
                unlock(lockKey);
            }
        }
        return null;
    }

    /**
     * 执行带锁的操作（默认等待 1 秒，持有 30 秒）
     */
    public <T> T executeWithLock(String lockKey, Supplier<T> action) {
        return executeWithLock(lockKey, 1, 30, action);
    }
}
