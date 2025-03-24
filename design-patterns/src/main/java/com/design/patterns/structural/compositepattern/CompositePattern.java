package com.design.patterns.structural.compositepattern;

import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName CompositePattern
 * @Description 测试组合模式
 * @date 2025/3/24 14:53
 **/
public class CompositePattern {

    public static void main(String[] args) {
        AbstractFile root = new Folder("root");

        AbstractFile folderA = new Folder("folderA");
        AbstractFile folderB = new Folder("folderB");

        AbstractFile  fileC = new File("fileC");
        AbstractFile  fileD = new File("fileD");
        AbstractFile  fileE = new File("fileE");

        root.Add(folderA);
        root.Add(folderB);
        root.Add(fileC);

        folderA.Add(fileD);
        folderA.Add(fileE);

        print(root);
    }

    public static void print(AbstractFile file) {
        file.printName();

        List<AbstractFile> childrenList = file.getChildren();
        if (childrenList == null) return;

        for (AbstractFile children : childrenList) {
            print(children);
        }
    }
}