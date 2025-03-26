package com.design.patterns.behavioural.strategy;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName AddStrategy
 * @Description 两数加法策略
 * @date 2025/3/26 10:33
 **/
public class AddStrategy implements Strategy{

    @Override
    public void TwoNumberOperation(int a, int b) {
        System.out.println(a + b);
    }
}