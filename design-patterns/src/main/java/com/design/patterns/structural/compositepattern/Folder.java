package com.design.patterns.structural.compositepattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Folder
 * @Description 创建 容器节点 Folder，表示树形结构中的容器节点
 * @date 2025/3/24 14:51
 **/
public class Folder extends AbstractFile {

    private List<AbstractFile> childrenList = new ArrayList<AbstractFile>();

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public boolean Add(AbstractFile file) {
        return childrenList.add(file);
    }

    @Override
    public boolean Remove(AbstractFile file) {
        return childrenList.remove(file);
    }

    @Override
    public List<AbstractFile> getChildren() {
        return childrenList;
    }
}