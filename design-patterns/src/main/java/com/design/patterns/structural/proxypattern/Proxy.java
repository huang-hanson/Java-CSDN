package com.design.patterns.structural.proxypattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Proxy
 * @Description 代理 Proxy
 * @date 2025/3/24 19:17
 **/
public class Proxy implements Subject {

    protected RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void buy() {
        System.out.println("办理购买前的手续");
        realSubject.buy();
        System.out.println("办理购买后的手续");
    }
}