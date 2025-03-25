package com.design.patterns.behavioural.observer;

/**
 * @author hanso
 * @description: 被观察者接口
 * @date 2025-03-25 23:39:08
 * @version: 1.0
 */
public interface Subject {// 目标
    public void Attach(Observer observer);// 添加观察者
    public void Detach(Observer observer);// 删除观察者
    public void Notify();// 状态改变后 通知所有观察者
    public void setState(String state);// 设置状态（改变状态）
    public String getState();// 获取状态
}
