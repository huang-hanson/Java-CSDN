package com.hanson.java.base.mysqllock.impl;


import cn.hutool.core.date.DateUtil;
import com.hanson.java.base.mysqllock.UnionKeyLock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName UnionKeyLockDemo
 * @date 2024/12/18 16:57
 **/
@Service
public class UnionKeyLockImpl implements UnionKeyLock {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean lock(String lockName, Integer second) {
        try {
            String sql = String.format("insert into union_key_lock (lock_name, expire_at) value ('%s','%s')", lockName, DateUtil.formatLocalDateTime(LocalDateTime.now().plusSeconds(second)));
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void unLock(String lockName) {
        String sql = String.format("delete from union_key_lock where lock_name='%s';", lockName);
        jdbcTemplate.execute(sql);
    }

}