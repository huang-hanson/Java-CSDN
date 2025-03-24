package com.design.patterns.structural.facadepattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Facade
 * @Description 外观类
 * @date 2025/3/24 17:04
 **/
public class Facade {

    SubSystemOne subSystemOne;
    SubSystemTwo subSystemTwo;
    SubSystemThree subSystemThree;

    public Facade() {
        subSystemOne = new SubSystemOne();
        subSystemTwo = new SubSystemTwo();
        subSystemThree = new SubSystemThree();
    }

    public void methodA() {
        subSystemOne.methodOne();
    }

    public void methodB() {
        subSystemTwo.methodTwo();
    }

    public void methodC() {
        subSystemThree.methodThree();
    }
}