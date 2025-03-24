package com.design.patterns.structural.facadepattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName FacadePattern
 * @Description 测试外观模式
 * @date 2025/3/24 17:08
 **/
public class FacadePattern {

    public static void main(String[] args) {
        Facade facade = new Facade();

        facade.methodA();
        facade.methodB();
        facade.methodC();
    }
}