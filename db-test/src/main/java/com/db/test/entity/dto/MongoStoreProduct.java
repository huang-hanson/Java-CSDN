package com.db.test.entity.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MongoStoreProduct
 * @Description Mongo实体
 * @date 2025/2/17 17:41
 **/
@Data
@Document("storeproductinfo")
public class MongoStoreProduct {

    /**
     * 文档的id使用ObjectId类型来封装，并且贴上@Id注解
     */
    @Id
    @Field("_id")
    @JsonProperty("_id")
    private String id;

    /**
     * 图片
     */
    @Field("image")
    @JsonProperty("image")
    private String image;

    /**
     * 轮播图片
     */
    @Field("sliderImage")
    @JsonProperty("sliderImage")
    private String sliderImage;

    /**
     * 店铺名称
     */
    @Field("storeName")
    @JsonProperty("storeName")
    private String storeName;

    /**
     * 店铺信息
     */
    @Field("storeInfo")
    @JsonProperty("storeInfo")
    private String storeInfo;

    /**
     * 关键词
     */
    @Field("keyword")
    @JsonProperty("keyword")
    private String keyword;

    /**
     * 分类ID
     */
    @Field("cateId")
    @JsonProperty("cateId")
    private String cateId;

    /**
     * 单位名称
     */
    @Field("unitName")
    @JsonProperty("unitName")
    private String unitName;

    /**
     * 排序
     */
    @Field("sort")
    @JsonProperty("sort")
    private Integer sort;

    /**
     * 是否热门
     */
    @Field("isHot")
    @JsonProperty("isHot")
    private Boolean isHot;

    /**
     * 是否有优惠
     */
    @Field("isBenefit")
    @JsonProperty("isBenefit")
    private Boolean isBenefit;

    /**
     * 是否精品
     */
    @Field("isBest")
    @JsonProperty("isBest")
    private Boolean isBest;

    /**
     * 是否新品
     */
    @Field("isNew")
    @JsonProperty("isNew")
    private Boolean isNew;

    /**
     * 是否好评
     */
    @Field("isGood")
    @JsonProperty("isGood")
    private Boolean isGood;

    /**
     * 赠送积分
     */
    @Field("giveIntegral")
    @JsonProperty("giveIntegral")
    private Integer giveIntegral;

    /**
     * 是否子店铺
     */
    @Field("isSub")
    @JsonProperty("isSub")
    private Boolean isSub;

    /**
     * 虚拟销量
     */
    @Field("ficti")
    @JsonProperty("ficti")
    private Integer ficti;

    /**
     * 模板ID
     */
    @Field("tempId")
    @JsonProperty("tempId")
    private Integer tempId;

    /**
     * 规格类型
     */
    @Field("specType")
    @JsonProperty("specType")
    private Boolean specType;

    /**
     * 活动
     */
    @Field("activity")
    @JsonProperty("activity")
    private String activity;

    /**
     * 属性
     */
    @Field("attr")
    @JsonProperty("attr")
    private String attr;

    /**
     * 属性值
     */
    @Field("attrValue")
    @JsonProperty("attrValue")
    private String attrValue;

    /**
     * 内容
     */
    @Field("content")
    @JsonProperty("content")
    private String content;

    /**
     * 优惠券ID列表
     */
    @Field("couponIds")
    @JsonProperty("couponIds")
    private String couponIds;

    /**
     * 平铺模式
     */
    @Field("flatPattern")
    @JsonProperty("flatPattern")
    private String flatPattern;
}