package com.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.entity.SeckillUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀用户 Mapper 接口
 */
@Mapper
public interface SeckillUserMapper extends BaseMapper<SeckillUser> {
}
