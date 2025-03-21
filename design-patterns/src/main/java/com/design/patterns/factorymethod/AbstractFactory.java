package com.design.patterns.factorymethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName AbstractFactory
 * @Description main
 * @date 2025/3/21 15:31
 **/
public class AbstractFactory {

    public static void main(String[] args) {
        Factory1 factory1 = new Factory1();

        ProductA productA = factory1.createProductA();
        productA.info();

        Factory2 factory2 = new Factory2();

        ProductB productB = factory2.createProductB();
        productB.info();
    }
}