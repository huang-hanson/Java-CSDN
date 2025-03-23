package com.design.patterns.creative.builderpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanson
 * @description: 定义产品类
 * @date 2025-03-23 21:08:03
 * @version: 1.0
 */
public class Product {

    List<String> parts = new ArrayList<String>();

    public void Add(String part) {
        parts.add(part);
    }

    public void show() {
        System.out.println("产品的组成：");
        for (String part : parts) {
            System.out.println(part + " ");
        }
        System.out.println("\n");
    }
}
