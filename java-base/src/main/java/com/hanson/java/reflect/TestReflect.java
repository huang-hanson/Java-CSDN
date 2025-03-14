package com.hanson.java.reflect;

import java.lang.reflect.Field;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestRefect
 * @Description TODO
 * @date 2025/3/14 14:40
 **/
public class TestReflect {

    public static void main(String[] args) {
//        Student student = new Student("hanson", "huang");
//        System.out.println(student);
//        System.out.println(student.getName());
//        student.sayHello();
////        student.sayHello("huang");
//        System.out.println(student.getNickName());

        // 反射获取
        try {
            Class<?> clazz = Class.forName("com.hanson.java.reflect.Student");
            Field name = clazz.getDeclaredField("name");
            name.setAccessible(true);
            String o = (String) name.get(new Student());
            System.out.println(o);

        } catch (ClassNotFoundException | NoSuchFieldException e){

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}