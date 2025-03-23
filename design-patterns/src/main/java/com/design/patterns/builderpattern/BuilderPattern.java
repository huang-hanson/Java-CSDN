package com.design.patterns.builderpattern;

/**
 * @author hanson
 * @description:
 * @date 2025-03-23 21:20:19
 * @version: 1.0
 */
public class BuilderPattern {

    public static void main(String[] args) {
        Director director = new Director();

        Builder builder1 = new Builder1();
        director.Construct(builder1);
        Product product1 = builder1.getResult();
        product1.show();

        Builder builder2 = new Builder2();
        director.Construct(builder2);
        Product product2 = builder2.getResult();
        product2.show();
    }
}
