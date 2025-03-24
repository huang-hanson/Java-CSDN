package com.design.patterns.structural.flyweightpattern;

import java.util.Random;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName FlyWeightPattern
 * @Description 测试享元模式
 * @date 2025/3/24 18:26
 **/
public class FlyWeightPattern {

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Random random = new Random();
        String[] colors = {"red", "blue", "green", "white", "black"};

        for (int i = 1; i <= 100; i++) {
            int x = random.nextInt(colors.length);
            Shape shape = factory.getShape(colors[x]);

            System.out.print("第" + i + "个圆：");
            shape.draw(random.nextInt(2025), random.nextInt(324));
        }
    }
}