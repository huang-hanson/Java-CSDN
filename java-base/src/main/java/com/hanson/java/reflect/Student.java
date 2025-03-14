package com.hanson.java.reflect;

import lombok.Data;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Student
 * @Description 学生类
 * @date 2025/3/14 14:39
 **/
@Data
public class Student {

    private String name;

    public String nickName;

    public Student(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    public Student() {
    }

    public void sayHello() {
        System.out.println("hello, " + name);
    }

    private void sayHello(String nickName) {
        System.out.println("hello, " + nickName);
    }

}