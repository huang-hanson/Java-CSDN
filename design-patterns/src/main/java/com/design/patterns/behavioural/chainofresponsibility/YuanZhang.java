package com.design.patterns.behavioural.chainofresponsibility;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName YuanZhang
 * @Description YuanZhang处理者
 * @date 2025/3/25 15:51
 **/
public class YuanZhang extends Handler {

    @Override
    public void HandlerRequest(int request) {
        if (request <= 15) {
            System.out.println("院长审批通过");
        } else {
            if (next != null) {
                next.HandlerRequest(request);
            } else {
                System.out.println("无法审批");
            }
        }
    }
}