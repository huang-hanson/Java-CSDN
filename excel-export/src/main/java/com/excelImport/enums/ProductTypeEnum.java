package com.excelImport.enums;

import lombok.Getter;

/**
 * @author hanson.huang=
 * @Description 产品分类枚举
 * @date 2025/6/26 10:09
 **/
@Getter
public enum ProductTypeEnum {

    WY_SELF_CLASS("0100", "无忧课堂自营课"),
    WY_HY_CLASS("0200", "无忧课堂HY课程"),
    WY_NON_HY_CLASS("0300", "无忧课堂非HY课程");

    private final String code;
    private final String desc;

    ProductTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getByCode(String code) {
        for (ProductTypeEnum type : ProductTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type.getDesc();
            }
        }
        return "";
    }
}