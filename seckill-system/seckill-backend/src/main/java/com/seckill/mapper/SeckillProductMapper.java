package com.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.entity.SeckillProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 秒杀商品 Mapper 接口
 */
@Mapper
public interface SeckillProductMapper extends BaseMapper<SeckillProduct> {

    /**
     * 扣减库存
     * @param productId 商品 ID
     * @return 影响行数
     */
    int decreaseStock(@Param("productId") Long productId);

    /**
     * 增加已售数量
     * @param productId 商品 ID
     * @return 影响行数
     */
    int increaseSoldCount(@Param("productId") Long productId);
}
