package com.design.patterns.structural.bridgepattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ProductA
 * @Description 扩展抽象部分 ProductA
 * @date 2025/3/24 10:48
 **/
public class ProductA extends Product {

    @Override
    public void Operation() {
        color.OperationImp(this.getName());
    }
}