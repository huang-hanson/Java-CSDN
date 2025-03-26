package com.design.patterns.behavioural.strategy;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MultiplyStrategy
 * @Description 两数乘法策略
 * @date 2025/3/26 10:36
 **/
public class MultiplyStrategy implements Strategy {

    @Override
    public void TwoNumberOperation(int a, int b) {
        System.out.println(a * b);
    }
}