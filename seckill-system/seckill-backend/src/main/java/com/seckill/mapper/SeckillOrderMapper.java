package com.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.entity.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀订单 Mapper 接口
 */
@Mapper
public interface SeckillOrderMapper extends BaseMapper<SeckillOrder> {
}
