package com.design.patterns.factorymethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName FactoryA
 * @Description 具体工厂1，用于创建具体对象A1，B1
 * @date 2025/3/21 15:35
 **/
public class Factory1 implements Factory{

    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB1();
    }
}