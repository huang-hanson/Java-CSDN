package com.seckill.common;

import lombok.Getter;

/**
 * 响应状态码枚举
 */
@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),

    // 秒杀相关错误码
    SECKILL_NOT_STARTED(1001, "秒杀尚未开始"),
    SECKILL_ALREADY_ENDED(1002, "秒杀已经结束"),
    SECKILL_STOCK_NOT_ENOUGH(1003, "库存不足"),
    SECKILL_ALREADY_PURCHASED(1004, "您已经购买过该商品"),
    SECKILL_ORDER_NOT_FOUND(1005, "秒杀订单不存在"),
    SECKILL_REPEAT(1006, "重复请求"),

    // 用户相关错误码
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_LIMIT_EXCEEDED(2002, "超过限购次数");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
