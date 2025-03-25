package com.design.patterns.behavioural.mediator;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ConcreteMediator
 * @Description 具体中介者
 * @date 2025/3/25 19:41
 **/
public class ConcreteMediator implements Mediator{

    private Colleague1 colleague1;
    private Colleague2 colleague2;

    public void setColleague1(Colleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(Colleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    @Override
    public void sendMessage(String message, Colleague colleague) {
        if (colleague == colleague1) {
            colleague2.Notify(message);
        } else {
            colleague1.Notify(message);
        }
    }
}