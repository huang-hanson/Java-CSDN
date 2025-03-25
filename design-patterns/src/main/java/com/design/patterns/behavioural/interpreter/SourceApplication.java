package com.design.patterns.behavioural.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SourceApplication
 * @Description 源码中解释器模式应用
 * @date 2025/3/25 18:05
 **/
public class SourceApplication {

    public static void main(String[] args) {
        // 定义正则表达式
        String regex = "a+";
        // 编译正则表达式，创建 Pattern 对象
        Pattern pattern = Pattern.compile(regex);
        // 要匹配的输入字符串
        String input = "aaab";
        // 创建 Matcher 对象，用于在输入字符串中进行匹配
        Matcher matcher = pattern.matcher(input);
        // 执行匹配操作
        if (matcher.find()) {
            System.out.println("找到匹配的内容：" + matcher.group());
        } else {
            System.out.println("未找到匹配的内容。");
        }
    }

}