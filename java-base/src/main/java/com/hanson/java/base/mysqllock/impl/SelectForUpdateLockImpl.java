package com.hanson.java.base.mysqllock.impl;

import com.hanson.java.base.mysqllock.SelectForUpdateLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SelectForUpdateLockImpl
 * @date 2024/12/18 17:42
 **/
@Slf4j
@Service
public class SelectForUpdateLockImpl implements SelectForUpdateLock {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private PlatformTransactionManager platformTransactionManager;
    public void lock(String lockName, Runnable runnable)  {
        // 定义事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 开启事务
        TransactionStatus status = platformTransactionManager.getTransaction(def);
        try {
            // 尝试获取锁
            jdbcTemplate.queryForObject("SELECT lock_name FROM select_for_update_lock WHERE lock_name = ? FOR UPDATE", String.class, lockName);
            runnable.run();;
        } catch (Exception e) {
            // 出现异常时回滚事务
            platformTransactionManager.rollback(status);
            throw e;
        }finally {
            // 提交事务，释放锁
            platformTransactionManager.commit(status);
        }
    }
}