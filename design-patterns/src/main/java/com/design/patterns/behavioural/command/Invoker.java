package com.design.patterns.behavioural.command;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Invoker
 * @Description 请求者
 * @date 2025/3/25 16:40
 **/
public class Invoker { // 请求者

    private Command command;// 命令

    public void setCommand(Command command) { // 设置请求者 的 请求的命令
        this.command = command;
    }

    public void call() { // 调用
        command.Execute();
    }
}