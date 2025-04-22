package com.demo.shardingsphere.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.shardingsphere.entity.bo.OrderInfoSharding;
import com.demo.shardingsphere.entity.bo.UserInfoSharding;
import com.demo.shardingsphere.entity.vo.OrderInfoVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName OrderInfoShardingService
 * @Description 订单分表接口
 * @date 2025/4/22 16:46
 **/
public interface OrderInfoShardingService extends IService<OrderInfoSharding> {
    List<OrderInfoSharding> randomAddOrder();

    OrderInfoVO getByCreateTimeRange(LocalDateTime startTime, LocalDateTime endTime);
}
