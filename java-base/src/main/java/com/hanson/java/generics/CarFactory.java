package com.hanson.java.generics;

/**
 * @author hanson.huang
 * @version V1.0
 * @date 2024/10/30 19:35
 **/
public class CarFactory{
    public CarFactory(){

    }
    public Car car;
    public CarFactory(Car car){
        this.car  = car;
    }
}

//public class CarFactory<T extends Car> {
//    public CarFactory(){
//
//    }
//    public T car;
//    public CarFactory(T car){
//        this.car  = car;
//    }
//}