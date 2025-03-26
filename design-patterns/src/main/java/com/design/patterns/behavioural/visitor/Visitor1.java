package com.design.patterns.behavioural.visitor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Visitor1
 * @Description 访问者1
 * @date 2025/3/26 14:09
 **/
public class Visitor1 implements Visitor { // 访问者1：统计年龄总和

    private int studentAgeSum = 0;
    private int teacherAgeSum = 0;

    public int getStudentAgeSum() {
        return studentAgeSum;
    }

    public int getTeacherAgeSum() {
        return teacherAgeSum;
    }


    @Override
    public void visitStudent(Student student) {
        System.out.println("访问者1访问学生：" + student.getName() + " 年龄：" + student.getAge());
        studentAgeSum += student.getAge();
    }

    @Override
    public void visitTeacher(Teacher teacher) {
        System.out.println("访问者1访问老师：" + teacher.getName() + " 年龄：" + teacher.getAge());
        teacherAgeSum += teacher.getAge();
    }
}