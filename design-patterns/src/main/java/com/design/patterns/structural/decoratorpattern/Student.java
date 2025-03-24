package com.design.patterns.structural.decoratorpattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Student
 * @Description 具体组件
 * @date 2025/3/24 15:41
 **/
public class Student extends Person {

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void Operation() {
        System.out.println(name + "的职责：学习 ");
    }
}