package com.csdn.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csdn.dev.entity.User;
import com.csdn.dev.mapper.SelectOneDemoMapper;
import com.csdn.dev.service.SelectOneDemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SelectOneDemoServiceImpl
 * @Description TODO
 * @date 2024/12/25 19:42
 **/
@Slf4j
@Service
public class SelectOneDemoServiceImpl extends ServiceImpl<SelectOneDemoMapper, User> implements SelectOneDemoService {

    @Resource
    private SelectOneDemoMapper selectOneDemoMapper;


    @Override
    public User selectOne(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, user.getName());
        // 如果 age 有值，则按值查询；如果没有值，则查询 null
        if (StringUtils.isNotEmpty(user.getAge())) {
            queryWrapper.eq(User::getAge, user.getAge());
        } else {
            queryWrapper.isNull(User::getAge);
        }
        User user1 = selectOneDemoMapper.selectOne(queryWrapper);
        return user1;
    }
}