package com.design.patterns.behavioural.interpreter;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName NonterminalExpression
 * @Description 创建 NonterminalExpression（非终结符表达式）
 * @date 2025/3/25 17:42
 **/
public class NonterminalExpression implements Expression{

    private Expression region;
    private Expression person;

    public NonterminalExpression(Expression region, Expression person) {
        this.region = region;
        this.person = person;
    }

    @Override
    public boolean Interpret(String info) {
        String[] str = info.split("的");

        // B区的调试人员 --> str = {"B区", "调试人员"}
        return region.Interpret(str[0]) && person.Interpret(str[1]);
    }
}