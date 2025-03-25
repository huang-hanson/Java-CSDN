package com.design.patterns.behavioural.memento;

/**
 * @author hanso
 * @description: 测试备忘录模式
 * @date 2025-03-25 23:00:25
 * @version: 1.0
 */
public class MementoPattern {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();

        originator.setState("1024");
        Memento backup1 = originator.createMemento();
        caretaker.addMemento(backup1);

        originator.setState("2048");
        Memento backup2 = originator.createMemento();
        caretaker.addMemento(backup2);

        originator.setState("4096");
        Memento backup3 = originator.createMemento();
        caretaker.addMemento(backup3);

        System.out.println(originator.getState());

        caretaker.showMemento();

        Memento memento1 = caretaker.getMemento(2);
        originator.setMemento(memento1);

        System.out.println("根据第2次备份还原之后的状态为：" + originator.getState());
    }
}
