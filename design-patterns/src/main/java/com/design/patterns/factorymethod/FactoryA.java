package com.design.patterns.factorymethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName FactoryA
 * @Description 工厂A
 * @date 2025/3/21 17:01
 **/
public class FactoryA implements Factory{

    @Override
    public Product createProduct() {
        return new ProductA();
    }
}