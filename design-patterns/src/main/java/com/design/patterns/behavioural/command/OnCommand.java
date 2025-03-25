package com.design.patterns.behavioural.command;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName OnCommand
 * @Description 开机命令
 * @date 2025/3/25 16:42
 **/
public class OnCommand implements Command {

    private Tv tv;

    public OnCommand(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void Execute() {
        tv.onAction();
    }
}