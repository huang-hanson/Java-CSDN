package com.design.patterns.structural.compositepattern;

import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName File
 * @Description 创建 叶子节点 File，表示树形结构中的叶子节点
 * @date 2025/3/24 14:50
 **/
public class File extends AbstractFile {

    public File(String name) {
        this.name = name;
    }

    @Override
    public boolean Add(AbstractFile file) {
        return false;
    }

    @Override
    public boolean Remove(AbstractFile file) {
        return false;
    }

    @Override
    public List<AbstractFile> getChildren() {
        return null;
    }
}