package com.java.pdf.utils;

import java.io.File;

public class CommonUtils {


    public static String rootPath = getRootPath();

    /**
     * 创建文件对象
     * @param path 文件路径
     * @return 返回指定路径的文件对象
     */
    public static String getAbsolutePath(String path) {
        // 获取项目根目录
        String projectRoot = System.getProperty("user.dir"); // D:\code\java\Java-CSDN
        // 拼接目标目录路径
        File excelDir = new File(projectRoot, "java-pdf/src/main/resources/results/");
        File excelFile = new File(excelDir, path);
        return excelFile.getAbsolutePath();
    }

    /**
     * 获取项目根路径
     *
     * @return 返回项目根目录下java-pdf文件夹的绝对路径
     */
    public static String getRootPath() {
        // 获取项目根目录
        String projectRoot = System.getProperty("user.dir"); // D:\code\java\Java-CSDN
        // 拼接目标目录路径
        File excelDir = new File(projectRoot, "java-pdf/");
        return excelDir.getAbsolutePath();
    }

    public static void main(String[] args) {
        System.out.println(getAbsolutePath("test.pdf"));
    }

}