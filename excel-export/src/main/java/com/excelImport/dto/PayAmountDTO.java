package com.excelImport.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName PayAmountDTO
 * @Description 支付金额DTO
 * @date 2025/6/26 13:43
 **/
@Data
public class PayAmountDTO {

    private String payMethod;

    private String payMethodShow;

    private BigDecimal payAmount;

    private String payAmountShow;

    public void setPayAmountShow(BigDecimal payAmount) {
        this.payAmountShow = (payAmount != null) ? payAmount.setScale(2, BigDecimal.ROUND_DOWN).toString() : "0.00";
    }

}