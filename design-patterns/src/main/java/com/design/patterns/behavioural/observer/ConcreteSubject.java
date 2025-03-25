package com.design.patterns.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanso
 * @description: 具体被观察者
 * @date 2025-03-25 23:41:58
 * @version: 1.0
 */
public class ConcreteSubject implements Subject {
    private String name;
    private String state;

    private List<Observer> observerList;

    public ConcreteSubject(String name) {
        state = "未更新";
        this.name = name;

        observerList = new ArrayList<Observer>();
    }

    @Override
    public void Attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void Detach(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void Notify() {
        // for (遍历对象类型 对象名 : 遍历对象)
        for (Observer observer : observerList) {
            observer.update();
        }
    }

    @Override
    public void setState(String state) {
        this.state = state;

        System.out.println(name + "的状态发生变化，变化后的状态为：" + state);
        Notify();
    }

    @Override
    public String getState() {
        return state;
    }
}
