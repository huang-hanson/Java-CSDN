package com.excelImport.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ProductAmountDTO
 * @Description 产品账务DTO
 * @date 2025/6/26 16:27
 **/
@Data
public class ProductAmountDTO {
    /**
     * 产品类型编码
     * {@link com.excelImport.enums.ProductTypeEnum}
     */
    private String productType;
    /**
     * 产品类型名称
     */
    private String productTypeDesc;
    private BigDecimal totalIncome;
    private BigDecimal incomeOffset;

    private String totalIncomeShow;
    private String incomeOffsetShow;
}