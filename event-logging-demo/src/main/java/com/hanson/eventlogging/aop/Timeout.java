package com.hanson.eventlogging.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName Timeout
 * @Description TODO
 * @date 2024/7/30 17:20
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Timeout {
    long value(); // 超时时间，单位为毫秒
}
