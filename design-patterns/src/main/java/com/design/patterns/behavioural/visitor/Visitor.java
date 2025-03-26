package com.design.patterns.behavioural.visitor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Visitor
 * @Description 访问者接口
 * @date 2025/3/26 14:02
 **/
public interface Visitor {
    public void visitStudent(Student student); // 访问学生
    public void visitTeacher(Teacher teacher); // 访问老师
}