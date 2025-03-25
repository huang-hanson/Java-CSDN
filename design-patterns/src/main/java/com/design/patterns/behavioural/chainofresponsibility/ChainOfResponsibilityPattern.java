package com.design.patterns.behavioural.chainofresponsibility;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ChainOfResponsibilityPattern
 * @Description 测试责任链模式
 * @date 2025/3/25 15:54
 **/
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        FuDaoYuan fuDaoYuan = new FuDaoYuan();
        YuanZhang yuanZhang = new YuanZhang();
        XiaoZhang xiaoZhang = new XiaoZhang();

        fuDaoYuan.setNext(yuanZhang);
        yuanZhang.setNext(xiaoZhang);

        fuDaoYuan.HandlerRequest(31);
        fuDaoYuan.HandlerRequest(25);
    }
}