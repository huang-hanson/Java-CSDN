package com.design.patterns.behavioural.iterator;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Book
 * @Description 书籍类 - 聚合中的元素
 * @date 2025/3/25 19:07
 **/
public class Book {

    private String name;
    private double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}