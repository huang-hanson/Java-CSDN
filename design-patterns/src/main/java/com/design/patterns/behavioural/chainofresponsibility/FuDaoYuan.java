package com.design.patterns.behavioural.chainofresponsibility;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName FuDaoYuan
 * @Description FuDaoYuan处理者
 * @date 2025/3/25 15:47
 **/
public class FuDaoYuan extends Handler { // <= 7 审批

    @Override
    public void HandlerRequest(int request) {
        if (request <= 7) {
            System.out.println("辅导员审批通过");
        } else {
            if (next != null) {
                next.HandlerRequest(request);
            } else {
                System.out.println("无法审批");
            }
        }
    }
}