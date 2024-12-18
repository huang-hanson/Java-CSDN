package com.hanson.java.base.mysqllock.impl;

import com.hanson.java.base.mysqllock.OptimisticLock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName OptimisticLock
 * @date 2024/12/18 18:40
 **/
@Service
public class OptimisticLockImpl implements OptimisticLock {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public boolean lock(String lockName) {
        try {
            String sql = String.format("update optimistic_lock set lock_status=1, expire_at = NOW() + INTERVAL 1 MINUTE where lock_name ='%s' and lock_status = 0 ;", lockName);
            return jdbcTemplate.update(sql) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public void unLock(String lockName) {
        String sql = String.format("update optimistic_lock set lock_status=0 ,expire_at=now() where lock_name='%s' ;", lockName);
        jdbcTemplate.update(sql);
    }
}