package com.design.patterns.behavioural.state;

/**
 * @author hanso
 * @description: 状态A 有货
 * @date 2025-03-26 00:22:37
 * @version: 1.0
 */
public class StateA implements State { // 有货
    @Override
    public void Handle(Context context) {
        int count = context.getCount();

        if (count >= 1) {
            System.out.println("购买成功！");
            context.setCount(count - 1);

            if (context.getCount() == 0){
                context.setState(new StateB());
            }
        } else {
            System.out.println("购买失败！");
        }
    }
}
