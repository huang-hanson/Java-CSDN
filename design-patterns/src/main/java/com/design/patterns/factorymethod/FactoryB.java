package com.design.patterns.factorymethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName FactoryB
 * @Description 工厂B
 * @date 2025/3/21 17:01
 **/
public class FactoryB implements Factory {

    @Override
    public Product createProduct() {
        return new ProductB();
    }
}