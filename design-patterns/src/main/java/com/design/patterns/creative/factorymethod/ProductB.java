package com.design.patterns.creative.factorymethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ProductB
 * @Description 产品实现类B
 * @date 2025/3/21 17:01
 **/
public class ProductB extends Product {
//public class ProductB implements Product {

    @Override
    public void info() {
        System.out.println("产品的信息：B");
    }
}
