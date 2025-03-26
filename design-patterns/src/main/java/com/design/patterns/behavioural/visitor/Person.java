package com.design.patterns.behavioural.visitor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Person
 * @Description 抽象类, 定义accept方法接受访问者
 * @date 2025/3/26 14:04
 **/
public abstract class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract void Accept(Visitor visitor);
}