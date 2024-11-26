package com.hanson.java.base;

import java.util.Random;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName PublicVariant
 * @Description 变量
 * @date 2024/11/26 15:47
 **/
public class PublicVariant {

    private static double remainingRecords = 0.0;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            double v = new Random().nextDouble();
            System.out.println(v);
            remainingRecords += v;
        }

        System.out.println(remainingRecords);
    }


}