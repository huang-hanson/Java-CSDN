package com.hanson.freemarker.model;

import lombok.Data;

import java.util.Date;

/**
 * @author hanson.huang
 * @version V1.0
 * @date 2024/10/24 13:29
 **/
@Data
public class Price {
    private double price;
    private Date date;
}