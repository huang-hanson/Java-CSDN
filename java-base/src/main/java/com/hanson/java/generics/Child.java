package com.hanson.java.generics;

class Parent<T> {
    public void setValue(T value) {
    }
}

public class Child extends Parent<String> {
    @Override
    public void setValue(String value) {
    }
}





//class Parent {
//    public void setValue(Object value) {
//    }
//}
//
//public class Child extends Parent {
//    @Override
//    public void setValue(Object value) {
//    }
//}


