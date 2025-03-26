package com.design.patterns.behavioural.visitor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Visitor2
 * @Description 访问者2
 * @date 2025/3/26 14:11
 **/
public class Visitor2 implements Visitor {// 访问者2：求最高成绩和工龄

    private int maxScore = -1;
    private int maxWorkYear = -1;

    public int getMaxScore() {
        return maxScore;
    }

    public int getMaxWorkYear() {
        return maxWorkYear;
    }

    @Override
    public void visitStudent(Student student) {
        System.out.println("访问者2访问学生：" + student.getName() + " 成绩：" + student.getScore());
        maxScore = Math.max(maxScore, student.getScore());
    }

    @Override
    public void visitTeacher(Teacher teacher) {
        System.out.println("访问者2访问老师：" + teacher.getName() + " 工龄：" + teacher.getWorkYear());
        maxWorkYear = Math.max(maxWorkYear, teacher.getWorkYear());
    }
}