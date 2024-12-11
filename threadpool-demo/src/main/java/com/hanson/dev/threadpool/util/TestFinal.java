package com.hanson.dev.threadpool.util;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestFina
 * @date 2024/12/11 14:08
 **/
public class TestFinal {

    public static void main(String[] args) {
        int b = 0;
        for (int i = 0; i < 10; i++) {
            final int a = b;
            final int c = i;
            new Thread(() -> {
//                System.out.println(a);
            }).start();
            b++;
            System.out.println(a);
            System.out.println(c);
        }
    }

}