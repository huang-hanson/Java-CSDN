package com.design.patterns.structural.bridgepattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Blue
 * @Description 具体实现部分 Blue
 * @date 2025/3/24 10:49
 **/
public class Blue implements Color {

    @Override
    public void OperationImp(String name) {
        System.out.println(name + "：蓝色");
    }
}