package com.design.patterns.behavioural.strategy;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName OperationContext
 * @Description 策略引用
 * @date 2025/3/26 10:37
 **/
public class OperationContext {

    private Strategy strategy;

    public OperationContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void Operation(int a, int b) {
        strategy.TwoNumberOperation(a, b);
    }
}