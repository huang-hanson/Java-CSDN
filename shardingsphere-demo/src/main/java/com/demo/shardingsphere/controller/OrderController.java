package com.demo.shardingsphere.controller;

import com.csdn.dev.common.entity.dto.Result;
import com.demo.shardingsphere.entity.bo.OrderInfoSharding;
import com.demo.shardingsphere.entity.bo.UserInfoSharding;
import com.demo.shardingsphere.entity.vo.OrderInfoVO;
import com.demo.shardingsphere.service.OrderInfoShardingService;
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
 * @ClassName OrderController
 * @Description 订单控制器
 * @date 2025/4/22 16:49
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderInfoShardingService orderInfoShardingService;

    /**
     * 随机生成100条数据 模拟分库分表
     * @return 随机生成100条数据
     */
    @GetMapping("/add")
    public Result<List<OrderInfoSharding>> add() {
        List<OrderInfoSharding> orderInfoList = orderInfoShardingService.randomAddOrder();
        return Result.success(orderInfoList);
    }

    @GetMapping("/queryByTime")
    public Result<OrderInfoVO> queryByTimeRange(
            @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        OrderInfoVO orderList = orderInfoShardingService.getByCreateTimeRange(startTime, endTime);
        return Result.success(orderList);
    }
}