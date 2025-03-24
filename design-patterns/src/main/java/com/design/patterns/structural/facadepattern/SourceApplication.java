package com.design.patterns.structural.facadepattern;

import javax.swing.*;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SourceApplication
 * @Description 源码中外观模式应用
 * @date 2025/3/24 17:13
 **/
public class SourceApplication {

    public static void main(String[] args) {
        // 创建一个 JFrame 实例，JFrame 就是外观类
        JFrame frame = new JFrame("Swing Example");
        // 创建一个 JLabel 实例
        JLabel label = new JLabel("Hello, Swing!");
        // 将 JLabel 添加到 JFrame 中
        frame.add(label);
        // 设置 JFrame 的大小
        frame.setSize(300, 200);
        // 设置关闭窗口时的操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 使 JFrame 可见
        frame.setVisible(true);
    }
}