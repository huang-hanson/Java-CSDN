package com.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    
    @Select("SELECT c.id, c.user_id, c.product_id, c.quantity, c.checked, c.create_time, c.update_time, " +
            "p.id as pid, p.name as product_name, p.price as product_price, p.image as product_image, p.stock as product_stock, p.status as product_status " +
            "FROM cart c LEFT JOIN product p ON c.product_id = p.id " +
            "WHERE c.user_id = #{userId}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "productId", column = "product_id"),
        @Result(property = "quantity", column = "quantity"),
        @Result(property = "checked", column = "checked"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time"),
        @Result(property = "product.id", column = "pid"),
        @Result(property = "product.name", column = "product_name"),
        @Result(property = "product.price", column = "product_price"),
        @Result(property = "product.image", column = "product_image"),
        @Result(property = "product.stock", column = "product_stock"),
        @Result(property = "product.status", column = "product_status")
    })
    List<Cart> selectCartWithProductByUserId(Long userId);
}