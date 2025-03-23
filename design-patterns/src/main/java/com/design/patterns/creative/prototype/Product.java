package com.design.patterns.creative.prototype;

/**
 * @author hanson
 * @description: 体原型类
 * @date 2025-03-23 22:31:37
 * @version: 1.0
 */
public class Product implements Prototype {

    private int id;
    private double price;

    public Product() {

    }

    public Product(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public Object Clone() {
        Product object = new Product();
        object.id = this.id;
        object.price = this.price;

        return object;
    }
}
