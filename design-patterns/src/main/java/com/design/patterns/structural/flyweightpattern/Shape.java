package com.design.patterns.structural.flyweightpattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Shape
 * @Description 抽象享元 Shape
 * @date 2025/3/24 18:23
 **/
public abstract class Shape {

    protected String color;

    public abstract void draw(int x, int y);
}