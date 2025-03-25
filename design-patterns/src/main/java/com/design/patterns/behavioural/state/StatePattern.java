package com.design.patterns.behavioural.state;

/**
 * @author hanso
 * @description: 测试状态模式
 * @date 2025-03-26 00:25:38
 * @version: 1.0
 */
public class StatePattern {
    public static void main(String[] args) {
        Context context = new Context(); // count:3

        System.out.println(context.getState());

        context.Request(); // 购买一个饮料 count = 2
        context.Request(); // 购买一个饮料 count = 1
        context.Request(); // 购买一个饮料 count = 0

        System.out.println(context.getState());

        context.Request(); // 无货 等待补货 补货成功 count = 5

        System.out.println(context.getState());

        context.Request(); // 购买一个饮料 count = 4
        System.out.println(context.getCount());
    }
}
