package com.design.patterns.behavioural.mediator;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Colleague1
 * @Description 具体同事类1
 * @date 2025/3/25 19:40
 **/
public class Colleague1 extends Colleague {

    public Colleague1(Mediator mediator) {
        this.mediator = mediator;
    }

    public void sendMessage(String message) {
        mediator.sendMessage(message, this);
    }

    public void Notify(String message) {
        System.out.println("同事1收到消息：" + message);
    }
}