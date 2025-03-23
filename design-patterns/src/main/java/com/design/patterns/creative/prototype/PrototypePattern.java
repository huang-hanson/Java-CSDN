package com.design.patterns.creative.prototype;

/**
 * @author hanson
 * @description: 模拟客户端
 * @date 2025-03-23 22:33:22
 * @version: 1.0
 */
public class PrototypePattern {

    public static void main(String[] args) {
        Product product1 = new Product(2022, 5.28);
        System.out.println(product1.getId() + " " + product1.getPrice());

        // Product product2 = new Product(2022, 5.28);
        Product product2 = (Product) product1.Clone();
        System.out.println(product2.getId() + " " + product2.getPrice());

        Product product3 = (Product) product1.Clone();
        System.out.println(product3.getId() + " " + product3.getPrice());
    }
}
