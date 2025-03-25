package com.design.patterns.behavioural.command;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Tv
 * @Description 接收者 - 电视机
 * @date 2025/3/25 16:42
 **/
public class Tv {
    public void onAction() {
        System.out.println("电视机开机了...");
    }

    public void offAction() {
        System.out.println("电视机关机了...");
    }
}