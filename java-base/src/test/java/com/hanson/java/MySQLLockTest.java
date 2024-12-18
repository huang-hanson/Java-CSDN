package com.hanson.java;

import com.hanson.java.base.mysqllock.OptimisticLock;
import com.hanson.java.base.mysqllock.impl.SelectForUpdateLockImpl;
import com.hanson.java.base.mysqllock.impl.UnionKeyLockImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.stream.IntStream;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName UnionKeyLockTest
 * @date 2024/12/18 17:00
 **/
@Slf4j
@SpringBootTest
public class MySQLLockTest {

    @Resource
    private UnionKeyLockImpl unionKeyLock;

    @Resource
    private SelectForUpdateLockImpl selectForUpdateLock;

    @Resource
    private OptimisticLock optimisticLock;

    @Test
    void test_union_key_lock() {
        String lockName = "Hanson";
        IntStream.range(1, 5).parallel().forEach(x -> {
            try {
                if (unionKeyLock.lock(lockName, 5)) {
                    log.info("get lock success");
                } else {
                    log.warn("get lock error");
                }
            } finally {
                unionKeyLock.unLock(lockName);
            }
        });
    }

    @Test
    void test_select_for_update_lock() {
        String lockName = "Hanson";
        IntStream.range(1, 10).parallel().forEach(x -> {
            try {
                selectForUpdateLock.lock(lockName, () -> {
                    log.info("Thread {} got {} lock success", Thread.currentThread().getId(), lockName);
                    // 模拟一些需要锁保护的操作
                    try {
                        Thread.sleep(1000); // 例如，这里让线程休眠1秒来模拟操作
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (Exception e) {
                log.error("Thread {} got {} lock error", Thread.currentThread().getId(), lockName, e);
            }
        });
    }

    @Test
    void testOptimisticLock() {
        String lockName = "Hanson";
        IntStream.range(1, 10).parallel().forEach(x -> {
            try {
                if (optimisticLock.lock(lockName)) {
                    log.info("get lock success");
                } else {
                    log.warn("get lock error");
                }
            } finally {
                optimisticLock.unLock(lockName);
            }
        });
    }
}