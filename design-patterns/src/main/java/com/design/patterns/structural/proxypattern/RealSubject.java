package com.design.patterns.structural.proxypattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName RealSubject
 * @Description 真实主题 RealSubject
 * @date 2025/3/24 19:17
 **/
public class RealSubject implements Subject {

    @Override
    public void buy() {
        System.out.println("付钱");
    }
}