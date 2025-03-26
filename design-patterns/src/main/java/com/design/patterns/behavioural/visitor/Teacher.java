package com.design.patterns.behavioural.visitor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Teacher
 * @Description 具体元素 老师
 * @date 2025/3/26 14:08
 **/
public class Teacher extends Person {
    private int workYear;

    public Teacher(String name, int age, int workYear) {
        super(name, age);
        this.workYear = workYear;
    }

    public int getWorkYear() {
        return workYear;
    }

    @Override
    public void Accept(Visitor visitor) {
        visitor.visitTeacher(this);
    }
}