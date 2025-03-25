package com.design.patterns.behavioural.memento;

/**
 * @author hanso
 * @description: 原发器
 * @date 2025-03-25 22:56:35
 * @version: 1.0
 */
public class Originator {// 原发器
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    public void setMemento(Memento memento) {
        state = memento.getState();
    }
}
