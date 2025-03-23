package com.design.patterns.builderpattern;

/**
 * @author hanson
 * @description: 生成器（Builder 类）
 * @date 2025-03-23 21:10:30
 * @version: 1.0
 */
public abstract class Builder {

    public abstract void BuildPart();

    public abstract Product getResult();
}
