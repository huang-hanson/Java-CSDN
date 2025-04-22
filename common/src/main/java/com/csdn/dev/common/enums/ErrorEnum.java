package com.csdn.dev.common.enums;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ErrorEnum
 * @Description 错误代码枚举接口
 * @date 2025/4/22 14:06
 **/
public interface ErrorEnum {
    /**
     * 获取错误代码
     */
    Integer getCode();
    String getCodeStr();
    /**
     * 获取错误信息
     */
    String getMsg();
}