package com.encryption.algorithm.oneWayEncryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MD5
 * @Description
 * @date 2024/11/26 13:32
 *
 * MD5加密  MD5加密也是单向的，不能进行解密，需要双方都要加密，然后对比加密后的字符串
 * MD5加密属于 无密钥的哈希算法，也称为 消息摘要算法
 **/
public class MD5 {

    private static final String EXCPECTED_MD5 = "65a8e27d8879283831b664bd8b7f0ad4";

//    public static void main(String[] args) {
//        String content = "Hello, World!";
//
//        String md5 = generateMD5(content);
//        System.out.println("MD5验证通过，原始内容：" + content);
//        System.out.println("MD5值：" + md5);
//        System.out.println("期望的MD5值：" + EXCPECTED_MD5);
//        if (EXCPECTED_MD5.equals(md5)) {
//            System.out.println("MD5验证成功！");
//        } else {
//            System.out.println("MD5验证失败！");
//        }
//    }

    public static void main(String[] args) {
//        String content = "73101e08bdd5b0d78e1917aa96af6557";
//
//        String md5 = generateMD5(content);
//        System.out.println("MD5验证通过，原始内容：" + content);
//        System.out.println("MD5值：" + md5);
//        System.out.println("期望的MD5值：" + EXCPECTED_MD5);
//        if (EXCPECTED_MD5.equals(md5)) {
//            System.out.println("MD5验证成功！");
//        } else {
//            System.out.println("MD5验证失败！");
//        }
        System.out.println("Z3A1e2bEXM9TLKiA4w1Vtb8jqzq9tA9OmyG0I61nPlQ=".length());
        System.out.println("51a5f3124a8d5b0ce0de63362039c003".length());
        System.out.println(generateMD5("A5AD4102-3CA9-43D3-B209-F1E204B47BD9"));

        System.out.println(generateMD5("1668047595.875819999"));
        System.out.println(generateMD5("1668047708.371432"));
        System.out.println(generateMD5("1735787391"));


        System.out.println(generateMD5("SEMBDWXSS_dl_ios_kws_11"));
        System.out.println(generateMD5("华为Ads 哇棒移动_android"));
        System.out.println(generateMD5("bilibili_dl_and_xxl01"));
        System.out.println(generateMD5("bilibili_dl_and"));
        System.out.println(generateMD5("信息流01"));
    }


    /**
     * 计算 MD5 签名
     *
     * @param contentToMd5 JSON 格式的 post 数据
     * @return 签名结果（小写）
     */
    public static String generateMD5(String contentToMd5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(contentToMd5.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密异常：" + e.getMessage());
        }
    }

    /**
     * 验证 MD5 是否匹配
     *
     * @param content      原始内容
     * @param expectedMd5 期望的 MD5 哈希值
     * @return 如果内容的 MD5 值和期望的 MD5 值匹配，返回 true
     */
    public static boolean validateMD5(String content, String expectedMd5) {
        // 计算原始内容的 MD5 值
        String calculatedMd5 = generateMD5(content);
        // 比较计算出的 MD5 与期望的 MD5 是否一致
        return calculatedMd5.equals(expectedMd5);
    }
}