package com.design.patterns.structural.bridgepattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Product
 * @Description 产品抽象类
 * @date 2025/3/24 10:40
 **/
public abstract class Product {

    private String name;

    protected Color color;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void Operation();
}