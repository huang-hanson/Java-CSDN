package com.design.patterns.behavioural.state;

/**
 * @author hanso
 * @description: 上下文
 * @date 2025-03-26 00:21:08
 * @version: 1.0
 */
public class Context {// 贩卖机
    private int count;

    private State state;

    public Context() {
        count = 3;
        state = new StateA();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void Request() { // 购买一个饮料
        state.Handle(this);
    }
}
