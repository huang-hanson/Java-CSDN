package com.db.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.test.entity.bo.StoreProduct;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName StoreProductMapper
 * @Description 商品表mapper
 * @date 2025/2/17 14:27
 **/
@Mapper
public interface StoreProductMapper extends BaseMapper<StoreProduct> {
}
