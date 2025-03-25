package com.design.patterns.behavioural.mediator;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName Mediator
 * @Description 抽象中介者
 * @date 2025/3/25 19:38
 **/
public interface Mediator {
    void sendMessage(String message, Colleague colleague);
}