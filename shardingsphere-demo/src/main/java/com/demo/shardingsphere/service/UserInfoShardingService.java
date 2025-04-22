package com.demo.shardingsphere.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.shardingsphere.entity.bo.UserInfoSharding;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName UserInfoShardingService
 * @Description 用户信息接口
 * @date 2025/4/22 13:45
 **/
public interface UserInfoShardingService extends IService<UserInfoSharding> {

    List<UserInfoSharding> randomAddUser();

    UserInfoSharding getByUserId(Long userId);

    List<UserInfoSharding> getByCreateTimeRange(LocalDateTime startTime, LocalDateTime endTime);
}
