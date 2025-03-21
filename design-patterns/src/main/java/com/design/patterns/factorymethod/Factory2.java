package com.design.patterns.factorymethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Factory2
 * @Description 具体工厂2，用于创建具体对象A2，B2
 * @date 2025/3/21 15:40
 **/
public class Factory2 implements Factory{

    @Override
    public ProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB2();
    }
}