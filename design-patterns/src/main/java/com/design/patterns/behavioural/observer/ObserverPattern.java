package com.design.patterns.behavioural.observer;

/**
 * @author hanso
 * @description: 测试观察者模式
 * @date 2025-03-25 23:46:15
 * @version: 1.0
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Subject subjectA = new ConcreteSubject("目标A");

        ConcreteObserver observerB = new ConcreteObserver("张三", subjectA);
        ConcreteObserver observerC = new ConcreteObserver("李四", subjectA);
        ConcreteObserver observerD = new ConcreteObserver("王五", subjectA);

        subjectA.setState("更新了");

        System.out.println("======================================");

        subjectA.Detach(observerD);

        subjectA.setState("停更了");
    }
}
