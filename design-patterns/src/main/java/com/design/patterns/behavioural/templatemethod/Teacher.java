package com.design.patterns.behavioural.templatemethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Teacher
 * @Description 具体子类 Teacher
 * @date 2025/3/26 13:24
 **/
public class Teacher extends Person {

    @Override
    public void PrimitiveOperation1() {
        System.out.println("老师：上课 讲课 解答问题 布置作业");
    }

    @Override
    public void PrimitiveOperation2() {
        System.out.println("老师：批改作业 打分数");
    }
}