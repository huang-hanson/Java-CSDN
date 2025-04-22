package com.demo.shardingsphere.entity.vo;

import com.demo.shardingsphere.entity.bo.OrderInfoSharding;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName OrderInfoVO
 * @Description VO层
 * @date 2025/4/22 17:40
 **/
@Data
public class OrderInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer total;

    private List<OrderInfoSharding> data;
}