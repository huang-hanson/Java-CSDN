package com.design.patterns.behavioural.memento;

/**
 * @author hanson
 * @description: 备忘录
 * @date 2025-03-25 22:57:26
 * @version: 1.0
 */
public class Memento {// 备忘录
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
