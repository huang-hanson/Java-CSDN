package com.design.patterns.behavioural.strategy;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StrategyPattern
 * @Description 测试策略模式
 * @date 2025/3/26 10:38
 **/
public class StrategyPattern {
    public static void main(String[] args) {
        Strategy add = new AddStrategy();
        Strategy subtraction = new SubtractionStrategy();
        Strategy multiply = new MultiplyStrategy();

        OperationContext context = new OperationContext(add);
        context.Operation(2025, 524);

        context = new OperationContext(subtraction);
        context.Operation(2025, 524);

        context = new OperationContext(multiply);
        context.Operation(2025, 524);
    }
}