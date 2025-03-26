package com.design.patterns.behavioural.visitor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName VisitorPattern
 * @Description 测试访问者设计模式
 * @date 2025/3/26 14:16
 **/
public class VisitorPattern {
    public static void main(String[] args) {
        PersonStructure structure  = new PersonStructure();

        Visitor1 visitor1 = new Visitor1();
        System.out.println("访问者1的访问记录：");
        structure.Accept(visitor1);
        System.out.println("学生年龄的总和：" + visitor1.getStudentAgeSum() + " 老师年龄的总和：" + visitor1.getTeacherAgeSum());

        System.out.println("=========================================");

        Visitor2 visitor2 = new Visitor2();
        System.out.println("访问者2的访问记录：");
        structure.Accept(visitor2);
        System.out.println("学生的最高成绩：" + visitor2.getMaxScore() + " 老师的最高工龄：" + visitor2.getMaxWorkYear());
    }
}