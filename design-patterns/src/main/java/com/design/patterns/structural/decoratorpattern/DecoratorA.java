package com.design.patterns.structural.decoratorpattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DecoratorA
 * @Description 具体装饰器 DecoratorA
 * @date 2025/3/24 15:42
 **/
public class DecoratorA extends Decorator {

    public DecoratorA(Person person) {
        this.person = person;
    }

    @Override
    public void Operation() {// 职责
        person.Operation();// 原本的职责
        System.out.print("写作业 ");
    }
}