package com.design.patterns.behavioural.visitor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Student
 * @Description 具体元素 学生
 * @date 2025/3/26 14:07
 **/
public class Student extends Person {
    private int score;

    public Student(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void Accept(Visitor visitor) {
        visitor.visitStudent(this);
    }
}