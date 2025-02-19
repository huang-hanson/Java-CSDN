package com.db.test.entity.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StoreProductEsDTO
 * @Description ES数据
 * @date 2025/2/17 15:53
 **/
@Data
@Document(indexName = "store_product_info_v2")
//@Document(indexName = "#{@StoreProductServiceImpl.getStoreProductEsIndexName()}")
@Setting(settingPath = "es/StoreProductSettings.json")
public class StoreProductEsDTO {

    /**
     * id
     */
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    /**
     * 图片
     */
    @Field(value = "image", type = FieldType.Text)
    private String image;
    /**
     * 滑块图片
     */
    @Field(value = "slider_image", type = FieldType.Text)
    private String sliderImage;
    /**
     * 店铺名称
     */
    @Field(value = "store_name", type = FieldType.Text)
    private String storeName;
    /**
     * 店铺信息
     */
    @Field(value = "store_info", type = FieldType.Text)
    private String storeInfo;
    /**
     * 关键词
     */
    @Field(value = "keyword", type = FieldType.Text)
    private String keyword;
    /**
     * 分类 ID
     */
    @Field(value = "cate_id", type = FieldType.Keyword)
    private String cateId;
    /**
     * 单位名称
     */
    @Field(value = "unit_name", type = FieldType.Text)
    private String unitName;
    /**
     * 排序
     */
    @Field(value = "sort", type = FieldType.Integer)
    private Integer sort;
    /**
     * 是否热门
     */
    @Field(value = "is_hot", type = FieldType.Boolean)
    private Boolean isHot;
    /**
     * 是否有优惠
     */
    @Field(value = "is_benefit", type = FieldType.Boolean)
    private Boolean isBenefit;
    /**
     * 是否精品
     */
    @Field(value = "is_best", type = FieldType.Boolean)
    private Boolean isBest;
    /**
     * 是否新品
     */
    @Field(value = "is_new", type = FieldType.Boolean)
    private Boolean isNew;
    /**
     * 是否优质
     */
    @Field(value = "is_good", type = FieldType.Boolean)
    private Boolean isGood;
    /**
     * 赠送积分
     */
    @Field(value = "give_integral", type = FieldType.Integer)
    private Integer giveIntegral;
    /**
     * 是否子项
     */
    @Field(value = "is_sub", type = FieldType.Boolean)
    private Boolean isSub;
    /**
     * 虚拟数据
     */
    @Field(value = "ficti", type = FieldType.Integer)
    private Integer ficti;
    /**
     * 模板 ID
     */
    @Field(value = "temp_id", type = FieldType.Integer)
    private Integer tempId;
    /**
     * 规格类型
     */
    @Field(value = "spec_type", type = FieldType.Boolean)
    private Boolean specType;
    /**
     * 活动
     */
    @Field(value = "activity", type = FieldType.Text)
    private String activity;
    /**
     * 属性
     */
    @Field(value = "attr", type = FieldType.Text)
    private String attr;
    /**
     * 属性值
     */
    @Field(value = "attr_value", type = FieldType.Text)
    private String attrValue;
    /**
     * 内容
     */
    @Field(value = "content", type = FieldType.Text)
    private String content;
    /**
     * 优惠券 ID 列表
     */
    @Field(value = "coupon_ids", type = FieldType.Text)
    private String couponIds;
    /**
     * 平铺模式
     */
    @Field(value = "flat_pattern", type = FieldType.Text)
    private String flatPattern;

}