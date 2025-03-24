package com.design.patterns.structural.compositepattern;

import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName AbstractFile
 * @Description 抽象组件,定义所有组件的通用接口
 * @date 2025/3/24 14:49
 **/
public abstract class AbstractFile {

    protected String name;

    public void printName() {
        System.out.println(name);
    }

    public abstract boolean Add(AbstractFile file);

    public abstract boolean Remove(AbstractFile file);

    public abstract List<AbstractFile> getChildren();
}