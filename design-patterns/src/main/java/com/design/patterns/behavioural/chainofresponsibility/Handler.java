package com.design.patterns.behavioural.chainofresponsibility;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Handler
 * @Description 抽象类定义了处理请求的接口和设置下一个处理者的方法
 * @date 2025/3/25 15:45
 **/
public abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void HandlerRequest(int request);
}