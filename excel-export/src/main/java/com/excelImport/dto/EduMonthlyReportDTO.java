package com.excelImport.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName EduMonthlyReportDTO
 * @Description 下载财务报表DTO
 * @date 2025/6/26 10:56
 **/
@Data
public class EduMonthlyReportDTO {

    /**
     * 财报具体数据
     */
    private List<TrainMonthlyReportDTO> financialReport;

    private Integer financialReportTotal;

    /**
     * 支付金额（销售收入） 总数
     */
    private BigDecimal payAmountCount;

    /**
     * 财务实收金额（订单收入） 总数
     */
    private BigDecimal orderIncomeCount;

    /**
     * 我们的总收入（如券使用时才算做我们的收入） 总数
     */
    private BigDecimal totalIncomeCount;

    /**
     * 收入抵减 总数
     * dailyincome * 合作映射表的百分比后，四舍五入至保留两位小数
     * （日表4位，月表2位）
     */
    private BigDecimal incomeOffsetCount;

    /**
     * 支付金额（销售收入） 总数
     */
    private String payAmountCountShow;

    /**
     * 财务实收金额（订单收入） 总数
     */
    private String orderIncomeCountShow;

    /**
     * 我们的总收入（如券使用时才算做我们的收入） 总数
     */
    private String totalIncomeCountShow;

    /**
     * 收入抵减 总数
     * dailyincome * 合作映射表的百分比后，四舍五入至保留两位小数
     * （日表4位，月表2位）
     */
    private String incomeOffsetCountShow;


    /**
     * 支付渠道收入
     */
    private List<PayAmountDTO> payChannelAmount;

    /**
     * 支付渠道收入 总额
     */
    private BigDecimal payChannelAmountCount;

    /**
     * 支付渠道收入 总额
     */
    private String payChannelAmountCountShow;

    /**
     * 产品账务
     */
    private List<ProductAmountDTO> productAmount;

    /**
     * 产品账务总收入
     */
    private BigDecimal productTotalIncomeCount;

    /**
     * 产品账务收入抵减
     */
    private BigDecimal productIncomeOffsetCount;

    /**
     * 产品账务总收入
     */
    private String productTotalIncomeCountShow;

    /**
     * 产品账务收入抵减
     */
    private String productIncomeOffsetCountShow;
}