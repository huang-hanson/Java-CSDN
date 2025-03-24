package com.design.patterns.structural.decoratorpattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DecoratorPattern
 * @Description 测试装饰器模式
 * @date 2025/3/24 15:46
 **/
public class DecoratorPattern {

    public static void main(String[] args) {
        Person zhangsan = new Student("张三");
        zhangsan= new DecoratorA(zhangsan);
        zhangsan= new DecoratorB(zhangsan);
        zhangsan.Operation();

        System.out.println("\n=====我是分割线=====");

        // 对象链
        Person lisi = new DecoratorB(new DecoratorA(new Student("李四")));
        lisi.Operation();
    }
}