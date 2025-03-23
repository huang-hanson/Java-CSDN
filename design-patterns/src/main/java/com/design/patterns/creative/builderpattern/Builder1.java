package com.design.patterns.creative.builderpattern;

/**
 * @author hanson
 * @description: 具体构建者1
 * @date 2025-03-23 21:17:56
 * @version: 1.0
 */
public class Builder1 extends Builder{

    Product product = new Product();

    @Override
    public void BuildPart() {
        product.Add("A");
        product.Add("B");
        product.Add("C");
        product.Add("D");
        product.Add("E");
        product.Add("F");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
