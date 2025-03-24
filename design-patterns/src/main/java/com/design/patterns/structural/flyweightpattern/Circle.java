package com.design.patterns.structural.flyweightpattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Circle
 * @Description 具体享元 Circle
 * @date 2025/3/24 18:24
 **/
public class Circle extends Shape {

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("draw a color：" + color + " circle x：" + x + " y：" + y);
    }
}