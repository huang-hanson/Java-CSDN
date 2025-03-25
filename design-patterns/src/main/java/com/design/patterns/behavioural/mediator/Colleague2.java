package com.design.patterns.behavioural.mediator;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Colleague2
 * @Description 具体同事类2
 * @date 2025/3/25 19:41
 **/
public class Colleague2 extends Colleague {

    public Colleague2(Mediator mediator) {
        this.mediator = mediator;
    }

    public void sendMessage(String message) {
        mediator.sendMessage(message, this);
    }

    public void Notify(String message) {
        System.out.println("同事2收到消息：" + message);
    }
}