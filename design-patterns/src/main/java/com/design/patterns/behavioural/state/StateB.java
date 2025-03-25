package com.design.patterns.behavioural.state;

/**
 * @author hanso
 * @description: 状态B 无货
 * @date 2025-03-26 00:24:22
 * @version: 1.0
 */
public class StateB implements State { // 无货
    @Override
    public void Handle(Context context) {
        int count = context.getCount();

        if (count == 0) {
            System.out.println("购买失败！等待补货");

            context.setCount(5);
            System.out.println("补货成功，请重新购买");
            context.setState(new StateA());
        }
    }
}
