package com.design.patterns.structural.adapterpattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName AdapterPattern
 * @Description 测试适配器模式
 * @date 2025/3/24 10:13
 **/
public class AdapterPattern {
    public static void main(String[] args) {
        USB usb = new Adapter();
        usb.Request();
    }
}