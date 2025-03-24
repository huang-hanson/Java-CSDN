package com.design.patterns.structural.decoratorpattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Person
 * @Description 抽象组件 Person
 * @date 2025/3/24 15:35
 **/
public abstract class Person {

    protected String name;

    public abstract void Operation();// 职责
}