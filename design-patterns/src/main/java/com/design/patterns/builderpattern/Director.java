package com.design.patterns.builderpattern;

/**
 * @author hanson
 * @description: 指挥者 负责组织 Builder 的构建过程。
 * @date 2025-03-23 21:16:35
 * @version: 1.0
 */
public class Director {

    public void Construct(Builder builder){
        builder.BuildPart();
    }
}
