package com.design.patterns.structural.bridgepattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BridgePattern
 * @Description 测试桥接模式
 * @date 2025/3/24 10:59
 **/
public class BridgePattern {
    public static void main(String[] args) {
        Product productA1 = new ProductA();
        Product productA2 = new ProductA();
        Red red = new Red();

        productA1.setName("产品A1");
        productA1.setColor(red);
        productA1.Operation();

        Blue blue = new Blue();
        productA2.setName("产品A2");
        productA2.setColor(blue);
        productA2.Operation();
    }
}