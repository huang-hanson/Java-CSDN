package com.design.patterns.creative.factorymethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ProductA
 * @Description 产品实现类A
 * @date 2025/3/21 17:01
 **/
public class ProductA extends Product{
//public class ProductA implements Product{

    @Override
    public void info() {
        System.out.println("产品的信息：A");
    }
}
