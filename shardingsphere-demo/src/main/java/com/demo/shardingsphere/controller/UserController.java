package com.demo.shardingsphere.controller;

import com.csdn.dev.common.entity.dto.Result;
import com.demo.shardingsphere.entity.bo.UserInfoSharding;
import com.demo.shardingsphere.service.UserInfoShardingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName UserController
 * @Description 用户控制器
 * @date 2025/4/22 13:55
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserInfoShardingService userInfoShardingService;

    /**
     * 随机生成100条数据 模拟分库分表
     * @return 随机生成100条数据
     */
    @GetMapping("/add")
    public Result<List<UserInfoSharding>> add() {
        List<UserInfoSharding> userInfo = userInfoShardingService.randomAddUser();
        return Result.success(userInfo);
    }

    /**
     * @return 查询所有数据
     */
    @GetMapping("/list")
    public Result<List<UserInfoSharding>> list() {
        List<UserInfoSharding> userInfo = userInfoShardingService.list();
        return Result.success(userInfo);
    }

    /**
     * 根据 userId 查询用户信息
     * @param userId 用户ID
     * @return 查询结果
     */
    @GetMapping("/get")
    public Result<UserInfoSharding> getByUserId(@RequestParam Long userId) {
        UserInfoSharding userInfo = userInfoShardingService.getByUserId(userId);
        return Result.success(userInfo);
    }

    @GetMapping("/queryByTime")
    public Result<List<UserInfoSharding>> queryByTimeRange(
            @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {

        List<UserInfoSharding> userList = userInfoShardingService.getByCreateTimeRange(startTime, endTime);
        return Result.success(userList);
    }


}