package com.design.patterns.behavioural.templatemethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Student
 * @Description 具体子类 Student
 * @date 2025/3/26 13:15
 **/
public class Student extends Person {

    @Override
    public void PrimitiveOperation1() {
        System.out.println("学生：听课 学习 做笔记 提出问题");
    }

    @Override
    public void PrimitiveOperation2() {
        System.out.println("学生：写作业 提交作业");
    }
}