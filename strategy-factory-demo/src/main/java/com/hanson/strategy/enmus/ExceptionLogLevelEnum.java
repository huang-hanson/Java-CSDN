package com.hanson.strategy.enmus;

import lombok.Getter;

@Getter
public enum ExceptionLogLevelEnum {
    //抛出错误时打印的错误日志级别
    ERROR(),
    WARN(),
    INFO(),
}

