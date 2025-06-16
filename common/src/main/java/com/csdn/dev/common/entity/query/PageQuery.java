package com.csdn.dev.common.entity.query;

import lombok.Data;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName PageQuery
 * @Description TODO
 * @date 2025/6/16 18:17
 **/
@Data
public class PageQuery {

    /**
     * 查询页面
     */
    private Integer page = 1;

    /**
     * 页面大小
     */
    private Integer pageSize = 1;
}