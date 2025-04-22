package com.demo.shardingsphere.entity.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName OrderInfoShardingMapper
 * @Description 订单分库表
 * @date 2025/4/22 16:36
 **/
@Data
@TableName("t_order_info")
public class OrderInfoSharding implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId("orderid")
    private Long orderId;

    /**
     * 用户id
     */
    @TableField("userid")
    private Long userId;

    /**
     * 订单金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 订单状态
     */
    @TableField("status")
    private String status;

    /**
     * 订单创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 订单更新时间
     */
    @TableField("update_time")
    private String updateTime;
}