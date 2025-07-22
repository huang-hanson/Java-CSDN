package com.excelImport.dto;

import com.excelImport.enums.ProductTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * @author hanson.huang
 * @date 2025/6/25 16:46
 **/
@Data
public class TrainMonthlyReportDTO {

    /**
     * 分摊月份（用每月一日表示月份）
     */
    @JsonDeserialize(using = YearMonthDeserializer.class)
    @JsonSerialize(using = YearMonthSerializer.class)
    @JsonFormat(pattern = "yyyy-MM")
    private YearMonth agrgtMonth;

    /**
     * EDU_课程id、EDU_课程id_IOS、PAC_礼包id、PAC_礼包id_IOS
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 支付金额（销售收入）
     */
    private BigDecimal payAmount;

    /**
     * 财务实收金额（订单收入）
     */
    private BigDecimal orderIncome;

    /**
     * 我们的总收入（如券使用时才算做我们的收入）
     */
    private BigDecimal totalIncome;

    /**
     * 收入抵减
     * dailyincome * 合作映射表的百分比后，四舍五入至保留两位小数
     * （日表4位，月表2位）
     */
    private BigDecimal incomeOffset;

    /**
     * 支付金额（销售收入）
     */
    private String payAmountShow;

    /**
     * 财务实收金额（订单收入）
     */
    private String orderIncomeShow;

    /**
     * 我们的总收入（如券使用时才算做我们的收入）
     */
    private String totalIncomeShow;

    /**
     * 收入抵减
     * dailyincome * 合作映射表的百分比后，四舍五入至保留两位小数
     * （日表4位，月表2位）
     */
    private String incomeOffsetShow;

    /**
     * 产品分类
     * 0100：无忧课堂自营课程
     * 0200：无忧课堂HY课程
     * 0300：无忧课堂非HY课程
     */
    private String productType;

    /**
     * 合作方ID
     * 合作方数据不可修改，只能删除或新增
     */
    private Integer collaboratorId;

    /**
     * 合作方简称
     * 合作方名称同id是一一对应的
     */
    private String nameAbbr;

    /**
     * 合作方全称
     */
    private String name;

    /**
     * 分成比例（1到99的整数，买课程才有，-1为固定分成）
     */
    private String proportion;
}