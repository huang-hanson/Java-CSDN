package com.design.patterns.structural.adapterpattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Adapter
 * @Description 适配器 Adapter
 * @date 2025/3/24 10:10
 **/
public class Adapter extends USB {
    private TypeC typeC = new TypeC();

    @Override
    public void Request() {
        typeC.SpecificRequest();
    }
}