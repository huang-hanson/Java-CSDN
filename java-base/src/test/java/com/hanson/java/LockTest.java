package com.hanson.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName LockTest
 * @date 2024/12/18 18:13
 **/
@ExtendWith(MockitoExtension.class)
public class LockTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/csdn-test";
    private static final String USER = "root";
    private static final String PASS = "123456";

    @Test
    void test_select_for_update_lock() throws Exception {
        String lockName = "Hanson";
        int numberOfThreads = 10;

        // 使用线程池来管理线程
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ConcurrentHashMap<String, Boolean> results = new ConcurrentHashMap<>();

        // 提交多个任务到线程池
        IntStream.range(1, numberOfThreads + 1).forEach(x -> {
            executorService.submit(() -> {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                    // 关闭自动提交，开启事务
                    conn.setAutoCommit(false);

                    try (PreparedStatement pstmt = conn.prepareStatement(
                            "SELECT lock_name FROM select_for_update_lock WHERE lock_name = ? FOR UPDATE")) {

                        pstmt.setString(1, lockName);
                        try (ResultSet rs = pstmt.executeQuery()) {
                            if (rs.next()) {
                                // 锁定成功，记录结果
                                results.put(Thread.currentThread().getName(), true);

                                // 模拟一些操作
                                Thread.sleep(1000); // 假设操作需要1秒

                                // 提交事务
                                conn.commit();
                            } else {
                                // 没有找到匹配的lock_name，但这里我们主要关注锁定行为
                                results.put(Thread.currentThread().getName(), false);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // 发生异常时回滚事务
                        try {
                            conn.rollback();
                        } catch (SQLException rollbackEx) {
                            rollbackEx.printStackTrace();
                        }
                    } finally {
                        // 释放CountDownLatch的计数
                        latch.countDown();
                    }
                } catch (SQLException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        // 等待所有线程完成
        latch.await();

        // 检查结果
        int lockedCount = (int) results.values().stream().filter(Boolean::booleanValue).count();
        assertEquals(1, lockedCount, "Only one thread should have been able to lock the row");

        // 关闭线程池
        executorService.shutdown();
    }
}