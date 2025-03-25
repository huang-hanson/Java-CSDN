package com.design.patterns.behavioural.chainofresponsibility;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName XiaoZhang
 * @Description XiaoZhang处理者
 * @date 2025/3/25 15:52
 **/
public class XiaoZhang extends Handler {

    @Override
    public void HandlerRequest(int request) {
        if (request <= 30) {
            System.out.println("校长审批通过");
        } else {
            if (next != null) {
                next.HandlerRequest(request);
            } else {
                System.out.println("无法审批");
            }
        }
    }
}