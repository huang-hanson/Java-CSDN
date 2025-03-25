package com.design.patterns.behavioural.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanso
 * @description: 管理者
 * @date 2025-03-25 22:58:35
 * @version: 1.0
 */
public class Caretaker {// 管理者

    private List<Memento> mementoList = new ArrayList<>();

    public void addMemento(Memento state) {
        mementoList.add(state);
    }

    public Memento getMemento(int index) {
        // 判断参数是否合法
        if (index >= 1 && index <= mementoList.size()) {
            return mementoList.get(index - 1);
        }

        return null;
    }

    public void showMemento() {
        int cnt = 1;
        // for (遍历对象类型 对象名 : 遍历对象)
        for (Memento memento : mementoList) {
            System.out.println("第" + cnt + "次备份，状态为：" + memento.getState());

            cnt++;
        }
    }
}
