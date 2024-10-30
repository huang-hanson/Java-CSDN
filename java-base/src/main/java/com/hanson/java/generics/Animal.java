package com.hanson.java.generics;

/**
 * @author hanson.huang
 * @version V1.0
 * @date 2024/10/30 19:27
 **/
public class Animal<T,U> {
    private T value;
    public static <T> T get(T... a){
        return a[a.length-1];
    }
    public T getFirst(){
        return value;
    }
}
//public class Animal<T,U> {
//    private String name;
//    private T mouth;
//    private U eyes;
//
//    public T getMouth(){
//        return mouth;
//    }
//}
//public class Animal<T> {
//    private String name;
//    private T mouth;
//
//    public T getMouth(){
//        return mouth;
//    }
//}