package com.design.patterns.abstractfactory;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName Factory
 * @Description 抽象工厂
 * @date 2025/3/21 15:31
 **/
public interface Factory {

    public ProductA createProductA();

    public ProductB createProductB();
}
