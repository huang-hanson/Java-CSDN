package com.design.patterns.behavioural.command;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName CommandPattern
 * @Description 测试命令模式
 * @date 2025/3/25 16:44
 **/
public class CommandPattern {
    public static void main(String[] args) {
        Tv tv = new Tv(); // 接收者对象 - 电视机

        Command onCommand = new OnCommand(tv); // 开机命令
        Command offCommand = new OffCommand(tv); // 关机命令

        Invoker invoker = new Invoker(); // 调用者

        invoker.setCommand(onCommand); // 设置开机命令
        invoker.call(); // 执行命令

        System.out.println("=======================");

        invoker.setCommand(offCommand); // 设置关机命令
        invoker.call(); // 执行命令
    }
}