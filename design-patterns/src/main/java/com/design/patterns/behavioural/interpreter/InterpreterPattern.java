package com.design.patterns.behavioural.interpreter;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName InterpreterPattern
 * @Description 测试解释器模式
 * @date 2025/3/25 17:47
 **/
public class InterpreterPattern {


    public static void main(String[] args) {
        Context context = new Context();

        context.check("A区的开发人员");
        context.check("B区的调试人员");
        context.check("C区的测试人员");

        System.out.println("==========");

        context.check("D区的程序员");
        context.check("D区的测试员");
        context.check("A区的程序员");
    }
}