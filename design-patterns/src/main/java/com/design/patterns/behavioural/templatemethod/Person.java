package com.design.patterns.behavioural.templatemethod;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Person
 * @Description 抽象类定义了模板方法TemplateMethod
 * @date 2025/3/26 13:14
 **/
public abstract class Person {

    public void TemplateMethod() {
        System.out.println("上课 去教室"); // 1
        PrimitiveOperation1(); // 2
        System.out.println("下课 离开教室"); // 3
        PrimitiveOperation2(); // 4
    }

    public abstract void PrimitiveOperation1(); // 原语操作 1 ：上课过程 学生 听课…… 老师 讲课
    public abstract void PrimitiveOperation2(); // 原语操作 2 ：作业     学生 写作业 提交作业…… 老师 批改作业 打分数

}