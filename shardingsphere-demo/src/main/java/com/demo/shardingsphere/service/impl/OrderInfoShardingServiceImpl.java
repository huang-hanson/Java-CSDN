package com.demo.shardingsphere.service.impl;

import cn.hutool.core.date.StopWatch;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.shardingsphere.entity.bo.OrderInfoSharding;
import com.demo.shardingsphere.entity.bo.UserInfoSharding;
import com.demo.shardingsphere.entity.vo.OrderInfoVO;
import com.demo.shardingsphere.mapper.OrderInfoShardingMapper;
import com.demo.shardingsphere.service.OrderInfoShardingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName OrderInfoShardingServiceImpl
 * @Description 订单分表实现类
 * @date 2025/4/22 16:47
 **/
@Service
public class OrderInfoShardingServiceImpl extends ServiceImpl<OrderInfoShardingMapper, OrderInfoSharding> implements OrderInfoShardingService {

    /**
     * 随机生成1000条数据 插入表中
     * @return 随机生成1000条数据
     */
    @Override
    public List<OrderInfoSharding> randomAddOrder() {
        List<OrderInfoSharding> orderList = new ArrayList<>();
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 订单状态选项
        String[] statuses = {"待支付", "已支付", "已发货", "已完成", "已取消", "退款中"};

        for (int i = 0; i < 1000; i++) {
            OrderInfoSharding order = new OrderInfoSharding();

            // 订单ID (从100000开始递增)
            order.setOrderId(100000L + i);

            // 用户ID (随机10000-99999之间的用户)
            order.setUserId(10000L + random.nextInt(90000));

            // 订单金额 (100-9999.99之间的随机金额)
            order.setAmount(new BigDecimal(100 + random.nextInt(9900))
                    .add(new BigDecimal(random.nextDouble()).setScale(2, BigDecimal.ROUND_HALF_UP)));

            // 订单状态 (随机选择)
            order.setStatus(statuses[random.nextInt(statuses.length)]);

            // 创建时间 (过去30天内的随机时间)
            LocalDateTime createTime = now.minusDays(random.nextInt(30))
                    .minusHours(random.nextInt(24))
                    .minusMinutes(random.nextInt(60));
            order.setCreateTime(createTime.format(formatter));

            // 更新时间 (在创建时间基础上随机增加0-7天)
            LocalDateTime updateTime = createTime.plusDays(random.nextInt(8))
                    .plusHours(random.nextInt(24))
                    .plusMinutes(random.nextInt(60));
            // 确保更新时间不超过当前时间
            if (updateTime.isAfter(now)) {
                updateTime = now;
            }
            order.setUpdateTime(updateTime.format(formatter));

            orderList.add(order);
        }

        // 改为单条插入
        for (OrderInfoSharding order : orderList) {
            this.save(order);
        }

        return orderList;
    }

    @Override
    public OrderInfoVO getByCreateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        StopWatch stopWatch = new StopWatch("测试分库分表时间");
        stopWatch.start("分库分表查询");
        List<OrderInfoSharding> list = this.lambdaQuery()
                .between(OrderInfoSharding::getCreateTime, startTime, endTime)
                .list();
        stopWatch.stop();
        log.warn(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));
        orderInfoVO.setTotal(list.size());
        orderInfoVO.setData(list);
        return orderInfoVO;
    }
}