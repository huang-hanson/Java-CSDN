package com.design.patterns.behavioural.templatemethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TemplateMethodPattern
 * @Description 测试模板方法模式
 * @date 2025/3/26 13:25
 **/
public class TemplateMethodPattern {

    public static void main(String[] args) {
        // 父类名 对象名 = new 子类名();
        Person student = new Student();
        Person teacher = new Teacher();

        student.TemplateMethod();

        System.out.println("=====我是分割线=====");

        teacher.TemplateMethod();
    }
}