package com.design.patterns.behavioural.observer;

/**
 * @author hanso
 * @description: 具体观察者
 * @date 2025-03-25 23:44:32
 * @version: 1.0
 */
public class ConcreteObserver implements Observer {
    private String name;
    private String state;

    private Subject subject;

    public ConcreteObserver(String name, Subject subject) {
        this.name = name;

        this.subject = subject;
        subject.Attach(this);

        state = subject.getState();
    }

    @Override
    public void update() {
        System.out.println(name + "收到通知");
        state = subject.getState(); // 让当前观察者的状态 和 改变了状态之后的目标的状态保持一致

        System.out.println(name + "改变后的状态为：" + state);
    }
}
