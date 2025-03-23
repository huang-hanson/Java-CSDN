package com.design.patterns.creative.builderpattern;

/**
 * @author hanson
 * @description: 具体构建者2
 * @date 2025-03-23 21:19:00
 * @version: 1.0
 */
public class Builder2 extends Builder{

    Product product = new Product();

    @Override
    public void BuildPart() {
        product.Add("A");
        product.Add("B");
        product.Add("C");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
