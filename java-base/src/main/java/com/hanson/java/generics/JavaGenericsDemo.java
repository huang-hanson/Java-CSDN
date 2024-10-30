package com.hanson.java.generics;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Java泛型
 *
 * @author hanson.huang
 * @version V1.0
 * @date 2024/10/30 19:16
 **/
public class JavaGenericsDemo {

//    public static void handleCar(List<? super   Car> cars){
//        Object object = cars.get(0);
//        // 编译报错
//        ElectricVehicle electricVehicle = cars.get(0);
//        Diesel diesel = cars.get(0);
//    }


//    public static void assemblyCar(List<? super   Car> cars){
//        System.out.println("Assembly car success!!!");
//        cars.add(new ElectricVehicle());
//    }


//    public static void main(String[] args) {
//        ArrayList<? extends Car> cars = new ArrayList<>();
//        cars.add(null);
//        cars.add(new Diesel());
//        cars.add(new ElectricVehicle());
//    }

//    public static void assemblyCar(List<? extends  Car> cars){
//        System.out.println("Assembly car success!!!");
//    }

//    public static void main(String[] args) {
//        //下面两行代码均可通过编译
//        assemblyCar(new ArrayList<Diesel>());
//        assemblyCar(new ArrayList<ElectricVehicle>());
//    }


//    public static void main(String[] args) {
//        Animal<Integer, Double> pair = new Animal<>();
//        Integer first = pair.getFirst();
//    }


//    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        if (list instanceof List){}
//        if (list instanceof List<String>){}
//        if (list instanceof List<Integer>){}
//        if (list instanceof T){}
//    }


//    public static void main(String[] args) {
//        ArrayList<Integer> numbers = new ArrayList<>();
//        ArrayList<String> characters = new ArrayList<>();
//        System.out.println(numbers.getClass() == characters.getClass());
//    }


//    public static void main(String[] args) {
//        ArrayList<String> objects = new ArrayList<>();
//        //we can do it like that
//        objects.add("Hello");
//        //wrong example
////        objects.add(new Date());
//    }


//    public static void main(String[] args) {
//        ArrayList<String> objects = new ArrayList<>();
//        objects.add("Hello");
//    }


//    public static void main(String[] args) {
//        ArrayList list = new ArrayList();
//        //强制类型转换
//        String res = (String) list.get(0);
//        //十分不安全的行为
//        list.add(new Date());
//    }
}