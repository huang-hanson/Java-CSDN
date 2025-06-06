package com.hanson.strategy.enmus;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName ErrorEnum
 * @Description TODO
 * @date 2024/7/30 19:46
 **/
public interface ErrorEnum {
    /**
     * 获取错误代码
     * @param
     * @return java.lang.String
     * @author linton.cao
     * @date 2021/9/2 10:24
     */
    Integer getCode();
    String getCodeStr();

    /**
     * 获取错误信息
     * @param
     * @return java.lang.String
     * @author linton.cao
     * @date 2021/9/2 10:27
     */
    String getMsg();
}
