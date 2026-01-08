package com.db.test.service.impl;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Redisson 看门狗（Watchdog）机制演示
 */
public class RedissonWatchdogDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1. 配置 Redisson 客户端
        Config config = new Config();
        // 使用单节点模式，连接到本地的 Redis。地址格式：redis://127.0.0.1:6379
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        // 【重要】设置看门狗的默认超时时间，默认为 30000 毫秒（30秒）
        // 为了方便演示，我们可以将其改小一点，比如10秒。
        // 这样，看门狗会每隔 10/3 ≈ 3.3秒 为锁续期一次。
        config.setLockWatchdogTimeout(10000);

        // 2. 创建 RedissonClient 实例
        RedissonClient redisson = Redisson.create(config);

        // 3. 定义锁的 key
        String lockKey = "my-distributed-lock";
        RLock lock = redisson.getLock(lockKey);

        System.out.println("主线程 [" + Thread.currentThread().getName() + "] 启动...");

        // 4. 启动一个子线程来模拟加锁和执行耗时任务
        new Thread(() -> {
            System.out.println("子线程 [" + Thread.currentThread().getName() + "] 尝试获取锁...");
            try {
                // 【核心代码】
                // 尝试加锁。这里没有指定 leaseTime（租约时间），因此会启用看门狗机制。
                // 看门狗的默认超时时间由 config.setLockWatchdogTimeout() 设置，为10秒。
                lock.lock();
                System.out.println("子线程 [" + Thread.currentThread().getName() + "] 成功获取到锁！");
                System.out.println("看门狗已启动，将开始执行一个非常耗时的任务（25秒）...");

                // 模拟一个长时间的业务操作，时长超过看门狗的超时时间（10秒）
                try {
                    Thread.sleep(25000); // 持续25秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("子线程 [" + Thread.currentThread().getName() + "] 耗时任务执行完毕。");

            } finally {
                // 检查锁是否被当前线程持有
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                    System.out.println("子线程 [" + Thread.currentThread().getName() + "] 手动释放锁。");
                }
            }
        }, "Worker-Thread").start();

        // 5. 主线程等待2秒，确保子线程已经开始尝试获取锁
        Thread.sleep(2000);

        // 6. 启动另一个线程，模拟竞争锁
        new Thread(() -> {
            System.out.println("竞争线程 [" + Thread.currentThread().getName() + "] 尝试获取锁...");
            boolean isLocked = lock.tryLock();
            if (isLocked) {
                System.err.println("【严重错误】竞争线程 [" + Thread.currentThread().getName() + "] 不应该能获取到锁！并发问题发生！");
                lock.unlock();
            } else {
                System.out.println("竞争线程 [" + Thread.currentThread().getName() + "] 获取锁失败，这是预期的结果，因为锁被 Worker-Thread 持有。");
            }
        }, "Competitor-Thread").start();


        // 让主线程等待足够长的时间，以观察整个过程
        Thread.sleep(30000);

        // 关闭 Redisson 客户端
        redisson.shutdown();
    }
}