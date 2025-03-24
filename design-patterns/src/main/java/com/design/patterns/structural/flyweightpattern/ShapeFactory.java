package com.design.patterns.structural.flyweightpattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ShapeFactory
 * @Description 享元工厂 ShapeFactory 管理享元对象
 * @date 2025/3/24 18:24
 **/
public class ShapeFactory {

    private Map<String, Shape> map = new HashMap<>();

    public Shape getShape(String key) {
        if (!map.containsKey(key)) {
            map.put(key, new Circle(key));
            System.out.println("create color：" + key + " circle");
        }
        return map.get(key);
    }
}