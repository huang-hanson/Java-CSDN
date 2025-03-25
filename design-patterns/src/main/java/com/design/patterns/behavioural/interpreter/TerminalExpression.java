package com.design.patterns.behavioural.interpreter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TerminalExpression
 * @Description 终结符表达式
 * @date 2025/3/25 17:38
 **/
public class TerminalExpression implements Expression {

    private Set<String> set = new HashSet<>();

    public TerminalExpression(String[] data) {
        // for (遍历对象类型 对象名 : 遍历对象)
        for (String str : data) {
            set.add(str);
        }
    }

    @Override
    public boolean Interpret(String info) {
        return set.contains(info);
    }
}