package com.db.test.entity.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StoreProduct
 * @Description 商品表
 * @date 2025/2/17 14:08
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class StoreProductRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String image;
    private String sliderImage;
    private String storeName;
    private String storeInfo;
    private String keyword;
    private String cateId;
    private String unitName;
    private Integer sort;
    private Boolean isHot;
    private Boolean isBenefit;
    private Boolean isBest;
    private Boolean isNew;
    private Boolean isGood;
    private Integer giveIntegral;
    private Boolean isSub;
    private Integer ficti;
    private Integer tempId;
    private Boolean specType;
    private List<String> activity;
    private List<Attribute> attr;
    private List<AttributeValue> attrValue;
    private String content;
    private List<Integer> couponIds;
    private String flatPattern;

    @Data
    public static class Attribute {
        private String attrName;
        private String attrValues;
    }

    @Data
    public static class AttributeValue {
        private Integer productId;
        private Integer stock;
        private String suk;
        private Double price;
        private String image;
        private Double cost;
        private Double otPrice;
        private Double weight;
        private Double volume;
        private Double brokerage;
        private Double brokerageTwo;
        private String attrValue;
        private Integer quota;
        private Integer quotaShow;
        private Double minPrice;
    }
}