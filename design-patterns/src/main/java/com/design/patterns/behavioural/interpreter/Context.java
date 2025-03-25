package com.design.patterns.behavioural.interpreter;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Context
 * @Description 上下文
 * @date 2025/3/25 17:44
 **/
public class Context {
    private String[] regions = {"A区", "B区", "C区"};
    private String[] persons = {"开发人员", "测试人员", "调试人员"};

    private NonterminalExpression nonterminal;

    public Context() {
        TerminalExpression region = new TerminalExpression(regions);
        TerminalExpression person = new TerminalExpression(persons);
        nonterminal = new NonterminalExpression(region, person);
    }

    public void check(String info) {
        boolean bool = nonterminal.Interpret(info);
        if (bool) {
            System.out.println("识别成功");
        } else {
            System.out.println("识别失败");
        }
    }
}