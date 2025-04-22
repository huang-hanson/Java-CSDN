package com.demo.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.shardingsphere.entity.bo.OrderInfoSharding;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName OrderInfoShardingMapper
 * @Description 订单分表mapper
 * @date 2025/4/22 16:45
 **/
@Mapper
public interface OrderInfoShardingMapper extends BaseMapper<OrderInfoSharding> {
}
