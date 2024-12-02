package com.hanson.java.base;

import java.util.Scanner;

public class ComplexCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("欢迎使用复杂计算器！");
        System.out.println("请输入第一个数字：");
        double num1 = scanner.nextDouble();

        System.out.println("请输入第二个数字：");
        double num2 = scanner.nextDouble();

        System.out.println("请选择操作：\n1. 加法\n2. 减法\n3. 乘法\n4. 除法");
        int choice = scanner.nextInt();

        double result = 0;
        boolean validOperation = true;

        switch (choice) {
            case 1:
                result = num1 + num2;
                break;
            case 2:
                result = num1 - num2;
                break;
            case 3:
                result = num1 * num2;
                break;
            case 4:
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("错误：除数不能为0！");
                    validOperation = false;
                }
                break;
            default:
                System.out.println("错误：无效的操作！");
                validOperation = false;
        }

        if (validOperation) {
            System.out.println("结果是：" + result);
        }

        scanner.close();
    }
}
