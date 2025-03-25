package com.design.patterns.behavioural.interpreter;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName Expression
 * @Description 抽象表达式
 * @date 2025/3/25 17:38
 **/
public interface Expression {
    public boolean Interpret(String info);
}
