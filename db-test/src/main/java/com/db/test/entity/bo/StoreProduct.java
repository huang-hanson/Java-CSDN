package com.db.test.entity.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StoreProduct
 * @Description 商品实体类
 * @date 2025/2/17 14:56
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("t_store_product")
public class StoreProduct implements Serializable {

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
    private String activity;
    private String attr;
    private String attrValue;
    private String content;
    private String couponIds;
    private String flatPattern;

}