package com.encryption.algorithm.twoWayEncryption;

import java.util.Base64;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Base64
 * @Description Base64算法
 * @date 2024/11/26 13:51
 **/
public class Base64Demo {

    public static void main(String[] args) {
        // URL 编码的 Base64 字符串
        String encodedStr = "FoKF02QWwM0DGCQyZmZkZTcwNy1kYmM1LTQ2ODAtOGI1Zi05Mzk3Nzc4M2ZlYmFYEDM0NmNhNjJjNWQyZWExZTkYBUNMSUNLAA%3D%3D"; // 你的字符串

        // URL-safe Base64 解码
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedStr);

        // 转换为字符串（如果解码结果是可读的文本）
        String decodedStr = new String(decodedBytes);

        // 打印解码结果
        System.out.println("Decoded String: " + decodedStr);
    }
}