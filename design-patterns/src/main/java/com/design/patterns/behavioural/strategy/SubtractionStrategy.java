package com.design.patterns.behavioural.strategy;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SubtractionStrategy
 * @Description 两数减法策略
 * @date 2025/3/26 10:35
 **/
public class SubtractionStrategy implements Strategy {

    @Override
    public void TwoNumberOperation(int a, int b) {
        System.out.println(a - b);
    }
}