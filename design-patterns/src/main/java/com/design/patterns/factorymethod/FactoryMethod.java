package com.design.patterns.factorymethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName FactoryMethod
 * @Description FactoryMethod Main
 * @date 2025/3/21 16:56
 **/
public class FactoryMethod {
    public static void main(String[] args) {

        // 父类 对象名 = new 子类();
        Factory factoryA = new FactoryA();

        Product productA = factoryA.createProduct();
//         Product productA = new ProductA();
        productA.info();

        Factory factoryB = new FactoryB();
        Product productB = factoryB.createProduct();
        productB.info();
    }
}