package com.design.patterns.structural.decoratorpattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DecoratorB
 * @Description 具体装饰器 DecoratorB
 * @date 2025/3/24 15:45
 **/
public class DecoratorB extends Decorator {

    public DecoratorB(Person person) {
        this.person = person;
    }

    @Override
    public void Operation() {// 职责
        person.Operation(); // 原本的职责
        System.out.print("考试 ");
    }
}