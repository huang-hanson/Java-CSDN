package com.demo.shardingsphere.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.shardingsphere.entity.bo.UserInfoSharding;
import com.demo.shardingsphere.mapper.UserInfoShardingMapper;
import com.demo.shardingsphere.service.UserInfoShardingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName UserInfoShardingServiceImpl
 * @Description 用户信息表-分表 实现类
 * @date 2025/4/22 13:46
 **/
@Service
public class UserInfoShardingServiceImpl extends ServiceImpl<UserInfoShardingMapper, UserInfoSharding> implements UserInfoShardingService {

    /**
     * 随机生成1000条数据 插入表中
     * @return 随机生成1000条数据
     */
    @Override
    public List<UserInfoSharding> randomAddUser() {
        List<UserInfoSharding> userList = new ArrayList<>();
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < 1000; i++) {
            UserInfoSharding user = new UserInfoSharding();
            user.setUserId((long) (100000 + i)); // 模拟userId
            user.setUserName("用户" + i);
            user.setUserAge(random.nextInt(60) + 18); // 18 - 77
            user.setUserSex(random.nextBoolean() ? "男" : "女");
            user.setUserPhone("138" + String.format("%08d", random.nextInt(100000000)));
            user.setUserEmail("user" + i + "@example.com");
            user.setUserAddress("地址_" + random.nextInt(1000));
            user.setViewTime(now.minusDays(random.nextInt(30)));
            // createTime 增加更多随机性
            user.setCreateTime(
                    now
                            .minusDays(random.nextInt(30))
                            .minusHours(random.nextInt(24))
                            .minusMinutes(random.nextInt(60))
            );
            user.setUpdateTime(now);

            userList.add(user);
        }

        // 批量插入数据库（假设你有 mapper 层）
        this.saveBatch(userList);

        return userList;
    }

    @Override
    public UserInfoSharding getByUserId(Long userId) {
        LambdaQueryWrapper<UserInfoSharding> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfoSharding::getUserId, userId);
        return this.getOne(queryWrapper);
    }

    /**
     * 根据开始时间和结束时间查用户信息
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 用户信息
     */
    @Override
    public List<UserInfoSharding> getByCreateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return this.lambdaQuery()
                .between(UserInfoSharding::getCreateTime, startTime, endTime)
                .list();
    }

}