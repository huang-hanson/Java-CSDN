package com.design.patterns.factorymethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ProductB1
 * @Description 抽象产品B1
 * @date 2025/3/21 15:37
 **/
public class ProductB1 implements ProductB{

    @Override
    public void info() {
        System.out.println("产品的信息：B1");
    }
}