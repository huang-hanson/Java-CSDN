package com.design.patterns.structural.flyweightpattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SourceApplication
 * @Description 源码应用
 * @date 2025/3/24 18:31
 **/
public class SourceApplication {
    public static void main(String[] args) {
        Integer a = Integer.valueOf(10);
        Integer b = Integer.valueOf(10);
        System.out.println(a == b); // 输出 true，因为使用了缓存的同一个对象

        Integer c = Integer.valueOf(128);
        Integer d = Integer.valueOf(128);
        System.out.println(c == d); // 输出 false，超出缓存范围，创建了不同的对象
    }
}