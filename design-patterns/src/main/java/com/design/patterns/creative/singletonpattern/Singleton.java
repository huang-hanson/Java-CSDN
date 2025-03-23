package com.design.patterns.creative.singletonpattern;

/**
 * @author hanson
 * @description:
 * @date 2025-03-23 23:37:21
 * @version: 1.0
 */
public class Singleton {

    private int number = 2025;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    // 1. 创建私有静态实例
    private static Singleton instance = new Singleton();

    // 2. 构造方法私有化，防止外部实例化
    private Singleton() {
    }

    // 3. 提供公共方法获取实例
    public static Singleton getInstance() {
        return instance;
    }
}
