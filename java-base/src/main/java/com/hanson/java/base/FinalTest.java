package com.hanson.java.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName FinalTest
 * @Description 测试final集合是否可变
 * @date 2025/3/24 17:51
 **/
public class FinalTest extends FinalList {

    public FinalTest() {
        this.list.add("1");
    }

    private static final List<Integer> finalList = new ArrayList<>();

    public static void main(String[] args) {
        finalList.add(1);
        finalList.add(2);

        finalList.forEach(System.out::println);

        System.out.println("分界线");

        FinalTest finalTest = new FinalTest();
        finalTest.list.add("2025");

        finalTest.list.forEach(System.out::println);
    }
}