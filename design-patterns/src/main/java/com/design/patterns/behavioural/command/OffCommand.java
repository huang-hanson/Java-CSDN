package com.design.patterns.behavioural.command;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName OffCommand
 * @Description 关机命令
 * @date 2025/3/25 16:43
 **/
public class OffCommand implements Command {

    private Tv tv;

    public OffCommand(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void Execute() {
        tv.offAction();
    }
}