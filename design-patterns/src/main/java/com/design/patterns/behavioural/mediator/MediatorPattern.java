package com.design.patterns.behavioural.mediator;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MediatorPattern
 * @Description 测试中介者模式
 * @date 2025/3/25 19:44
 **/
public class MediatorPattern {

    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        Colleague1 colleague1 = new Colleague1(mediator);
        Colleague2 colleague2 = new Colleague2(mediator);

        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);

        colleague1.sendMessage("软考加油");
        colleague2.sendMessage("祝大家软考顺利通过！");
    }
}