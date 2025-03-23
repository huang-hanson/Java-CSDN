package com.design.patterns.creative.singletonpattern;

/**
 * @author hanson
 * @description:
 * @date 2025-03-23 23:39:39
 * @version: 1.0
 */
public class SingletonPattern {

    public static void main(String[] args) {
        // Singleton singleton1 = new Singleton();  // 不能直接实例化

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        Singleton singleton3 = Singleton.getInstance();

        System.out.println(singleton1.getNumber() + " " + singleton2.getNumber() + " " + singleton3.getNumber());

        singleton1.setNumber(528);

        System.out.println(singleton1.getNumber() + " " + singleton2.getNumber() + " " + singleton3.getNumber());
    }
}
